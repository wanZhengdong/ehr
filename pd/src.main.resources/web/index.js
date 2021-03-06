require.config({
			paths : {
				jquery : "jquery.min",
				easyui : "jquery.easyui.min"
			},
			shim : {
				"easyui" : {
					deps : ["jquery"]
				}
			}
		});
require(['jquery', 'easyui', 'common', 'tree', 'db'], function(jquery, easyui,
				common, tree, db) {
			main = {
				init : function() {
					main.init$menu();
				},
				init$menu : function() {
					console.log("init$menu");
					$("#menu").tree({
								id : "id",
								text : "cn",
								pIdKey : "parentId",
								url : "dbTree/root?mid=menu",
								onClick : main.tabMenu
							});
				},
				tabMenu : function() {
					main.tabMenu$subNode();
					main.openModule();
				},
				tabMenu$subNode : function() {
					debugger;
					var treeItem = tree.selected("menu");
					if (!treeItem) {
						return;
					}
					var subNode = $("#menu").tree("getNode", treeItem);
					if (subNode) {
						return;
					}
					$.ajax({
								type : "POST",
								url : "dbTree/sub?mid=menu&id=" + treeItem.id,
								dataType : "JSON",
								success : function(data) {
									tree.refresh({
												treeId : "menu",
												parent : treeItem.target,
												data : data,
												onClickF : main.tabMenu
											});
								}
							});
				},
				tabMenu$refreshTab : function() {
					var treeItem = $("#menu").tree("getSelected");
					if (!treeItem) {
						return;
					}
					if (treeItem.url == null || treeItem.url == "") {
						return;
					}
					if (!$("#tt").tabs("exists", treeItem.cn)) {
						$('#tt').tabs('add', {
									title : treeItem.cn,
									href : treeItem.url,
									selected : true,
									closable : true
								});
					}
					$("#tt").tabs("select", treeItem.cn);
					setTimeout('$build("db.addEvent")', 500);
				},
				openModule : function() {
					var treeItem = tree.selected("menu");
					if (!treeItem) {
						return;
					}
					if (treeItem.url == null || treeItem.url == "") {
						return;
					}
					$('#moduleFrame')[0].src = "module.html?m=" + treeItem.id;
				}
			}
			main.init();
		});