package eclipsestarttime.actions;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IStartup;

/**
 * 统计eclipse启动耗时
 */
public class ShowTime implements IStartup {

	@Override
	public void earlyStartup() {
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
			public void run() {
				long eclipseStartTime = Long.parseLong(System.getProperty("eclipse.startTime"));
				long costTime = System.currentTimeMillis() - eclipseStartTime;
				Shell shell = Display.getDefault().getActiveShell();
				String message = "Eclipse启动耗时:" + costTime + "ms";
				MessageDialog.openInformation(shell, "Infomation", message);
			}
		});
	}

}
