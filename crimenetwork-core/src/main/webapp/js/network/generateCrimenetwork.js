/*
 * {
  "nodes" : {
    "S8831" : {
      "姓名" : "陈亚西",
      "籍贯" : "未知",
      "嫌疑人编号" : "R3209820000002003090003",
      "身份证" : "111111111111111111"
    },
    "S8830" : {
      "姓名" : "侯春楼",
      "籍贯" : "未知",
      "嫌疑人编号" : "R3209820000002003090002",
      "身份证" : "111111111111111111"
    },
    "S8833" : {
      "姓名" : "陈亚西",
      "籍贯" : null,
      "嫌疑人编号" : "R3209820020030910164181",
      "身份证" : "111111111111111111"
    },
    "S8832" : {
      "姓名" : "侯春楼",
      "籍贯" : "江苏涟水县",
      "嫌疑人编号" : "R3209820020030910164180",
      "身份证" : "111111111111111111"
    },
    "C21232" : {
      "案件编号" : "A3209820000002003090003",
      "案发地点" : "无"
    }
  },
  "edges" : [ {
    "fromId" : "S8830",
    "endId" : "C21232",
    "attributes" : { }
  }, {
    "fromId" : "S8831",
    "endId" : "C21232",
    "attributes" : { }
  }, {
    "fromId" : "C21232",
    "endId" : "S8831",
    "attributes" : { }
  }, {
    "fromId" : "C21232",
    "endId" : "S8832",
    "attributes" : { }
  }, {
    "fromId" : "C21232",
    "endId" : "S8830",
    "attributes" : { }
  }, {
    "fromId" : "S8832",
    "endId" : "C21232",
    "attributes" : { }
  }, {
    "fromId" : "C21232",
    "endId" : "S8833",
    "attributes" : { }
  }, {
    "fromId" : "S8833",
    "endId" : "C21232",
    "attributes" : { }
  } ]
}
 */
$(document).ready(function(){
	
	//var suspectId = $("input[name='suspect']").val();
	//var caseId = $("input[name='cases']").val();
	//var currencyId = $("input[name='currency']").val();
	$("#generate").click(function() {
		var suspectId = $("input[name='suspect']").val();
		var caseId = $("input[name='cases']").val();
		var currencyId = $("input[name='currency']").val();
		var count=0;
		if(suspectId!="") count++;
		if(caseId!="") count++;
		if(currencyId!="") count++;
		if(count>1) alert("只能输入一个查询项");
		if(count==0) alert("请输入一个查询项");
		$.ajax({
			type : "GET",
			url : "search",		
			success : function(data) {				
				draw2(data);
			}
		});
		
		
	});
	
});
function drawNetwork(data){
	var nodes = null;
	var edges = null;
	var network = null;
	var DIR = './lib/vis/img/network/';
	var LENGTH_MAIN = 350, LENGTH_SERVER = 150, LENGTH_SUB = 100, WIDTH_SCALE = 2, GREEN = 'green', RED = '#C5000B', ORANGE = 'orange',
	//GRAY = '#666666',
	GRAY = 'gray', BLACK = '#2B1B17';
	
	// Create a data table with nodes.
	nodes = [];

	// Create a data table with links.
	edges = [];
	

	nodes.push({
		id : 1,
		label : '嫌疑人A',
		group : 'suspect',
		value : 8,
		title : '籍贯：××省××市 <br/> 性别：× <br/> 嫌疑人编号：×××××××'
	});
	nodes.push({
		id : 2,
		label : '嫌疑人B',
		group : 'suspect',
		value : 8,
		title : '籍贯：××省××市 <br/> 性别：× <br/> 嫌疑人编号：×××××××'
	});
	nodes.push({
		id : 3,
		label : '嫌疑人C',
		group : 'suspect',
		value : 8,
		title : '籍贯：××省××市 <br/> 性别：× <br/> 嫌疑人编号：×××××××'
	});
	edges.push({
		from : 1,
		to : 2,
		length : LENGTH_MAIN,
		width : WIDTH_SCALE * 1,
		dashes : [ 5, 5, 3, 3 ],
		color : RED
	});
	edges.push({
		from : 1,
		to : 3,
		length : LENGTH_SUB,
		width : WIDTH_SCALE * 3,
		dashes : [ 2, 2, 10, 10 ],
		color : RED,
		label : '团伙'
	});

	nodes.push({
				id : 4,
				label : '案件A',
				group : 'cases',
				value : 8,
				title : '案发地点：××省××市 <br/> 案发时间：×年×月×日 <br/> 案件编号：××××××× <br/> 简要案情：××××××××'
	});
	nodes.push({
				id : 5,
				label : '案件B',
				group : 'cases',
				value : 8,
				title : '案发地点：××省××市 <br/> 案发时间：×年×月×日 <br/> 案件编号：××××××× <br/> 简要案情：××××××××'
	});
	nodes.push({
				id : 6,
				label : '案件C',
				group : 'cases',
				value : 8,
				title : '案发地点：××省××市 <br/> 案发时间：×年×月×日 <br/> 案件编号：××××××× <br/> 简要案情：××××××××'
	});
	
	nodes.push({
		id : 20,
		label : '通讯设备A',
		group : 'phone',
		value : 8,
		title : '通话记录：******'
	});
	
	edges.push({
		from : 1,
		to : 20,
		length : 200,
		width : WIDTH_SCALE * 1,	
		color : BLACK,
		title : '号码：138 8888 8888'
	});
	
	edges.push({
		from : 20,
		to : 2,
		length : 200,
		width : WIDTH_SCALE * 1,	
		color : BLACK,
		title : '号码：138 8888 3333'
	});
	
	edges.push({
		from : 4,
		to : 5,
		length : 400,
		width : WIDTH_SCALE * 2,
		dashes : [ 2, 2, 10, 10 ],
		color : BLACK,
		title : '通话记录：×××××××'
	});

	edges.push({
		from : 4,
		to : 5,
		length : 400,
		width : WIDTH_SCALE * 2,
		dashes : [ 2, 2, 10, 10 ],
		color : BLACK,
		title : '相似性分析如下：×××××××'
	});

	nodes.push({
		id : 7,
		label : '假币A',
		group : 'jiabi',
		value : 8,
		title : '冠字号：×××××× <br/> 假币编号：××××××× <br/> '
	});
	nodes.push({
		id : 8,
		label : '假币B',
		group : 'jiabi',
		value : 8,
		title : '冠字号：×××××× <br/> 假币编号：××××××× <br/> '
	});
	nodes.push({
		id : 9,
		label : '假币C',
		group : 'jiabi',
		value : 8,
		title : '冠字号：×××××× <br/> 假币编号：××××××× <br/> '
	});

	edges.push({
		from : 7,
		to : 9,
		length : LENGTH_MAIN,
		width : WIDTH_SCALE * 2,
		dashes : [ 2, 2, 10, 10 ],
		color : BLACK
	});
	edges.push({
		from : 2,
		to : 4,
		length : LENGTH_MAIN,
		width : WIDTH_SCALE * 2,
		dashes : [ 2, 2, 10, 10 ],
		color : ORANGE
	});

	edges.push({
		from : 1,
		to : 4,
		length : LENGTH_SERVER,
		color : GRAY,
		width : WIDTH_SCALE * 3,
		label : '重大嫌疑'
	});
	edges.push({
		from : 4,
		to : 7,
		length : LENGTH_SERVER,
		color : GRAY,
		width : WIDTH_SCALE * 3,
		label : '缴获'
	});

	edges.push({
		from : 2,
		to : 5,
		length : LENGTH_SERVER,
		color : GRAY,
		width : WIDTH_SCALE * 3,
		label : '重大嫌疑'
	});
	edges.push({
		from : 5,
		to : 8,
		length : LENGTH_SERVER,
		color : GRAY,
		width : WIDTH_SCALE * 3,
		label : '缴获'
	});

	edges.push({
		from : 3,
		to : 6,
		length : LENGTH_SERVER,
		color : GRAY,
		width : WIDTH_SCALE * 6,
		label : '重大嫌疑'
	});
	edges.push({
		from : 6,
		to : 9,
		length : LENGTH_SERVER,
		color : GRAY,
		width : WIDTH_SCALE * 3,
		label : '缴获'
	});

	// create a network
	var container = document.getElementById('mynetwork');
	var data = {
		nodes : nodes,
		edges : edges
	};
	var options = {
		nodes : {
			scaling : {
				min : 32,
				max : 32
			}
		},
		edges : {
			color : GRAY,
			smooth : false
		},
		physics : {
			barnesHut : {
				gravitationalConstant : -30000
			},
			stabilization : {
				iterations : 2500
			}
		},
		groups : {
			phone : {
				shape: 'image', 
				image: DIR + 'phone-icon.png',
				font : {
					size : 25,

				},
				size : 25,
				
			},
			suspect : {
				shape: 'image', 
				image: DIR + 'Person-icon.png',
				font : {
					size : 25,

				},
				size : 25,
				
			},
			cases : {
				shape: 'image', 
				image: DIR + 'case.png',
				font : {
					size : 25,

				},
				size : 25,
				
			},

			jiabi : {
				shape: 'image', 
				image: DIR + 'Money-icon.png',
				font : {
					size : 25,

				},
				size : 25,
				
			}
		}
	};
	network = new vis.Network(container, data, options);
	
}

function draw2(){
	var nodes = null;
	var edges = null;
	var network = null;
	var DIR = './lib/vis/img/network/';
	var LENGTH_MAIN = 350, LENGTH_SERVER = 150, LENGTH_SUB = 100, WIDTH_SCALE = 2, GREEN = 'green', RED = '#C5000B', ORANGE = 'orange',
	//GRAY = '#666666',
	GRAY = 'gray', BLACK = '#2B1B17';
	
	// Create a data table with nodes.
	nodes = [];

	// Create a data table with links.
	edges = [];

	nodes.push({
		id : 1,
		label : '嫌疑人A',
		group : 'suspect',
		value : 8,
		title : '籍贯：××省××市 <br/> 性别：× <br/> 嫌疑人编号：×××××××'
	});
	nodes.push({
		id : 2,
		label : '嫌疑人B',
		group : 'suspect',
		value : 8,
		title : '籍贯：××省××市 <br/> 性别：× <br/> 嫌疑人编号：×××××××'
	});
	nodes.push({
		id : 3,
		label : '嫌疑人C',
		group : 'suspect',
		value : 8,
		title : '籍贯：××省××市 <br/> 性别：× <br/> 嫌疑人编号：×××××××'
	});
	edges.push({
		from : 1,
		to : 2,
		length : LENGTH_MAIN,
		width : WIDTH_SCALE * 1,
		dashes : [ 5, 5, 3, 3 ],
		color : RED
	});
	edges.push({
		from : 1,
		to : 3,
		length : LENGTH_SUB,
		width : WIDTH_SCALE * 3,
		dashes : [ 2, 2, 10, 10 ],
		color : RED,
		label : '团伙'
	});

	nodes.push({
				id : 4,
				label : '案件A',
				group : 'cases',
				value : 8,
				title : '案发地点：××省××市 <br/> 案发时间：×年×月×日 <br/> 案件编号：××××××× <br/> 简要案情：××××××××'
	});
	nodes.push({
				id : 5,
				label : '案件B',
				group : 'cases',
				value : 8,
				title : '案发地点：××省××市 <br/> 案发时间：×年×月×日 <br/> 案件编号：××××××× <br/> 简要案情：××××××××'
	});
	nodes.push({
				id : 6,
				label : '案件C',
				group : 'cases',
				value : 8,
				title : '案发地点：××省××市 <br/> 案发时间：×年×月×日 <br/> 案件编号：××××××× <br/> 简要案情：××××××××'
	});
	
	nodes.push({
		id : 20,
		label : '通讯设备A',
		group : 'phone',
		value : 8,
		title : '通话记录：******'
	});
	
	edges.push({
		from : 1,
		to : 20,
		length : 200,
		width : WIDTH_SCALE * 1,	
		color : BLACK,
		title : '号码：138 8888 8888'
	});
	
	edges.push({
		from : 20,
		to : 2,
		length : 200,
		width : WIDTH_SCALE * 1,	
		color : BLACK,
		title : '号码：138 8888 3333'
	});
	
	edges.push({
		from : 4,
		to : 5,
		length : 400,
		width : WIDTH_SCALE * 2,
		dashes : [ 2, 2, 10, 10 ],
		color : BLACK,
		title : '通话记录：×××××××'
	});

	edges.push({
		from : 4,
		to : 5,
		length : 400,
		width : WIDTH_SCALE * 2,
		dashes : [ 2, 2, 10, 10 ],
		color : BLACK,
		title : '相似性分析如下：×××××××'
	});

	nodes.push({
		id : 7,
		label : '假币A',
		group : 'jiabi',
		value : 8,
		title : '冠字号：×××××× <br/> 假币编号：××××××× <br/> '
	});
	nodes.push({
		id : 8,
		label : '假币B',
		group : 'jiabi',
		value : 8,
		title : '冠字号：×××××× <br/> 假币编号：××××××× <br/> '
	});
	nodes.push({
		id : 9,
		label : '假币C',
		group : 'jiabi',
		value : 8,
		title : '冠字号：×××××× <br/> 假币编号：××××××× <br/> '
	});

	edges.push({
		from : 7,
		to : 9,
		length : LENGTH_MAIN,
		width : WIDTH_SCALE * 2,
		dashes : [ 2, 2, 10, 10 ],
		color : BLACK
	});
	edges.push({
		from : 2,
		to : 4,
		length : LENGTH_MAIN,
		width : WIDTH_SCALE * 2,
		dashes : [ 2, 2, 10, 10 ],
		color : ORANGE
	});

	edges.push({
		from : 1,
		to : 4,
		length : LENGTH_SERVER,
		color : GRAY,
		width : WIDTH_SCALE * 3,
		label : '重大嫌疑'
	});
	edges.push({
		from : 4,
		to : 7,
		length : LENGTH_SERVER,
		color : GRAY,
		width : WIDTH_SCALE * 3,
		label : '缴获'
	});

	edges.push({
		from : 2,
		to : 5,
		length : LENGTH_SERVER,
		color : GRAY,
		width : WIDTH_SCALE * 3,
		label : '重大嫌疑'
	});
	edges.push({
		from : 5,
		to : 8,
		length : LENGTH_SERVER,
		color : GRAY,
		width : WIDTH_SCALE * 3,
		label : '缴获'
	});

	edges.push({
		from : 3,
		to : 6,
		length : LENGTH_SERVER,
		color : GRAY,
		width : WIDTH_SCALE * 6,
		label : '重大嫌疑'
	});
	edges.push({
		from : 6,
		to : 9,
		length : LENGTH_SERVER,
		color : GRAY,
		width : WIDTH_SCALE * 3,
		label : '缴获'
	});

	// create a network
	var container = document.getElementById('mynetwork');
	var data = {
		nodes : nodes,
		edges : edges
	};
	var options = {
		nodes : {
			scaling : {
				min : 32,
				max : 32
			}
		},
		edges : {
			color : GRAY,
			smooth : false
		},
		physics : {
			barnesHut : {
				gravitationalConstant : -30000
			},
			stabilization : {
				iterations : 2500
			}
		},
		groups : {
			phone : {
				shape: 'image', 
				image: DIR + 'phone-icon.png',
				font : {
					size : 25,

				},
				size : 25,
				
			},
			suspect : {
				shape: 'image', 
				image: DIR + 'Person-icon.png',
				font : {
					size : 25,

				},
				size : 25,
				
			},
			cases : {
				shape: 'image', 
				image: DIR + 'case.png',
				font : {
					size : 25,

				},
				size : 25,
				
			},

			jiabi : {
				shape: 'image', 
				image: DIR + 'Money-icon.png',
				font : {
					size : 25,

				},
				size : 25,
				
			}
		}
	};
	network = new vis.Network(container, data, options);
}