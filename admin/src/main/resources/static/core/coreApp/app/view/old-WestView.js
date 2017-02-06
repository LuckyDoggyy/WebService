Ext.define("core.app.view.WestView", {
	extend : 'Ext.panel.Panel',
	alias : 'widget.westview',
	collapsible : true,
	split : true,
	border : 1,
	margins : '2 2 0 0',
	width : 200,
	minSize : 100,
	maxSize : 250,
	title : "系统菜单",
	layout : 'accordion',
	layoutConfig : {
		titleCollapse : true,
		animate : true,
		activeOnTop : true
	},
	items : [ {
		title : "考勤信息管理",
		items : [ {
			xtype : "treepanel",
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border : 0,
			root : {
				expanded : true,
				children : [  {
					id : 'deptmanage',
					text : '部门管理',
					leaf : true
				},  {
					id : 'banzumanage',
					text : '考勤班组管理',
					leaf : true
				}, {
					id : 'peomanage',
					text : '人员信息管理',
					leaf : true
				},{
					id : 'systempeosetting',
					text : '系统管理员管理',
					leaf : true
				}, {
					id : 'deptpeosetting',
					text : '部门管理员管理',
					leaf : true
				}, {
					id : 'kqpeosetting',
					text : '考勤员管理',
					leaf : true
				}, {
					id : 'holidaysetting',
					text : '节假日管理',
					leaf : true
				} ]
			}
		} ]
	}, {
		title : "日常事务管理",
		items : [ {
			xtype : "treepanel",
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border : 0,
			root : {
				expanded : true,
				children : [ {
					id : "kaoqinmanage",
					text : "考勤数据录入",
					leaf : true
				} ,{
					id : "banzupeoplemanage",
					text : "考勤班组人员管理",
					leaf : true
				}]
			}
		} ]
	}, {
		title : "查询与报表",
		items : [ {
			xtype : "treepanel",
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border : 0,
			root : {
				expanded : true,
				children : [ {
					id : "kqrecordreport",
					text : "考勤原始记录报表",
					leaf : true
				}, {
					id : "kqmonthreport",
					text : "考勤月统计报表",
					leaf : true
				}, {
					id : "kqpersonalreport",
					text : "个人考勤记录报表",
					leaf : true
				} ]
			}
		} ]
	}, {
		title : "系统管理",
		items : [ {
			xtype : "treepanel",
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border : 0,
			root : {
				expanded : true,
				children : [ {
					id : "backupandrestore",
					text : "数据备份与还原",
					leaf : true
				} ]
			}
		} ]
	} ],
	initComponent : function() {
		this.callParent(arguments);
	}
});
