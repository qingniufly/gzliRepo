package com.simon.servlet.server.send.event;

import java.io.IOException;
import java.util.Random;

import org.eclipse.jetty.servlets.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovementEventSource implements EventSource {

	private int width = 800;
	private int height = 600;
	private int stepMax = 5;
	private int x = 0;
	private int y = 0;
	private Random random = new Random();
	private Logger logger = LoggerFactory.getLogger(getClass().getName());

	public MovementEventSource(int width, int height, int stepMax) {
		this.width = width;
		this.height = height;
		this.stepMax = stepMax;
		this.x = random.nextInt(width);
		this.y = random.nextInt(height);
	}

	@Override
	public void onClose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOpen(Emitter emitter) throws IOException {
		query(emitter);
	}

	private void query(Emitter emitter) throws IOException {
		emitter.comment("Start sending movement information.");
		while (true) {
			emitter.comment("");
			move(); // 移动位置
			String id = String.format("%s,%s", x, y);
			// emitter.id(id); //根据位置生成事件标识符
			emitter.data(id); // 发送位置信息数据
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				logger.error("Movement query thread interrupted. Close the connection.", e);
				break;
			}
		}
		emitter.close(); // 当循环终止时，关闭连接
	}
	
	public void onResume(Emitter emitter, String lastEventId)
			throws IOException {
		updatePosition(lastEventId); //更新起始位置
		query(emitter);  //开始生成位置信息
	}

	// 根据Last-Event-Id来更新起始位置
	private void updatePosition(String id) {
		if (id != null) {
			String[] pos = id.split(",");
			if (pos.length > 1) {
				int xPos = -1, yPos = -1;
				try {
					xPos = Integer.parseInt(pos[0], 10);
					yPos = Integer.parseInt(pos[1], 10);
				} catch (NumberFormatException e) {

				}
				if (isValidMove(xPos, yPos)) {
					x = xPos;
					y = yPos;
				}
			}
		}
	}

	// 获取下一个合法的移动位置
	private void move() {
		while (true) {
			int[] move = getMove();
			int xNext = x + move[0];
			int yNext = y + move[1];
			if (isValidMove(xNext, yNext)) {
				x = xNext;
				y = yNext;
				break;
			}
		}
	}

	// 判断当前的移动位置是否合法
	private boolean isValidMove(int x, int y) {
		return x >= 0 && x <= width && y >= 0 && y <= height;
	}

	// 随机生成下一个移动位置
	private int[] getMove() {
		int[] xDir = new int[] { -1, 0, 1, 0 };
		int[] yDir = new int[] { 0, -1, 0, 1 };
		int dir = random.nextInt(4);
		return new int[] { xDir[dir] * random.nextInt(stepMax), yDir[dir] * random.nextInt(stepMax) };
	}

}
