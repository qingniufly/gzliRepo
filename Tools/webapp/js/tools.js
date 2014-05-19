var appleIdMap = {
		"CH" : "国行",
		"ZP" : "港行",
		"KH" : "韩版",
		"LL" : "美版",
		"DN" : "德版",
		"TA" : "台湾",
		"ZA" : "新加坡和马来西亚",
		"AB" : "阿联酋",
		"RS" : "俄罗斯",
		"GR" : "希腊",
		"IP" : "意大利",
		"PP" : "菲律宾",
		"FB" : "法国",
		"C" : "加拿大",
		"X" : "澳版",
		"B" : "英版",
		"F" : "法国",
		"J" : "日本",
		"Y" : "西班牙"
		};

$(function() {
	$('#appleId').blur(function() {
		var appleId = $(this).val();
		if (appleId.length > 0) {
			var re = /\/A$/;
			if (re.test(appleId)) {
				appleId = appleId.substring(0, appleId.length - 2);
			}
			var last2 = appleId.substring(appleId.length - 2, appleId.length);
			var last1 = appleId.substring(appleId.length - 1, appleId.length);
			if (appleIdMap[last2] != undefined) {
				alert(appleIdMap[last2]);
			} else if (appleIdMap[last1] != undefined) {
				alert(appleIdMap[last1]);
			} else {
				alert("未查询到相应型号！");
			}
		}
	});
});
