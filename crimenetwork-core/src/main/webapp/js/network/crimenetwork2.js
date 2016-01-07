$(document).ready(function(){
	var nodes = null;
	var edges = null;
	var network = null;
	var DIR = './lib/vis/img/network/';
	var LENGTH_MAIN = 350, LENGTH_SERVER = 150, LENGTH_SUB = 100, WIDTH_SCALE = 2, GREEN = 'green', RED = '#C5000B', ORANGE = 'orange',
	//GRAY = '#666666',
	GRAY = 'gray', BLACK = '#2B1B17';

	// Called when the Visualization API is loaded.
	function draw(){
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
			length : LENGTH_SERVER,
			width : WIDTH_SCALE * 2,
			dashes : [ 2, 2, 10, 10 ],
			color : BLACK
		});
		edges.push({
			from : 2,
			to : 4,
			length : LENGTH_SERVER,
			width : WIDTH_SCALE * 2,
			color : ORANGE
		});

		edges.push({
			from : 1,
			to : 4,
			length : LENGTH_SERVER,
			color : GRAY,
			width : WIDTH_SCALE,
			
		});
		edges.push({
			from : 3,
			to : 6,
			length : LENGTH_SERVER,
			color : GRAY,
			width : WIDTH_SCALE
			
		});
		edges.push({
			from : 4,
			to : 7,
			length : LENGTH_SERVER,
			color : GRAY,
			width : WIDTH_SCALE * 3,
			
		});

		edges.push({
			from : 2,
			to : 5,
			length : LENGTH_SERVER,
			color : GRAY,
			width : WIDTH_SCALE * 3,
			
		});
		edges.push({
			from : 5,
			to : 8,
			length : LENGTH_SERVER,
			color : GRAY,
			width : WIDTH_SCALE * 3,
			
		});

		edges.push({
			from : 3,
			to : 6,
			length : LENGTH_SERVER,
			color : GRAY,
			width : WIDTH_SCALE * 6,
			
		});
		edges.push({
			from : 6,
			to : 9,
			length : LENGTH_SERVER,
			color : GRAY,
			width : WIDTH_SCALE * 3,
			
		});
		
		edges.push({
			from : 6,
			to : 8,
			length : LENGTH_SERVER,
			color : GRAY,
			width : WIDTH_SCALE * 3,
			
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
	draw();
	
});