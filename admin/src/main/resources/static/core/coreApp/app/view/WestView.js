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
		title : "基本信息管理",
		items : [ {
			xtype : "treepanel",
			id : 'basicinfomanage',
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border : 0,
			store : Ext.create("Ext.data.TreeStore", {
				defaultRootId : 'basicinfomanage', // 默认的根节点id
				model : "core.app.model.TreeModel",
				proxy : {
					type : "ajax", // 获取方式
					url : "getMenu" // 获取树节点的地址
				},
				clearOnLoad : false,
				nodeParam : "node"// 设置传递给后台的参数名,值是树节点的id属性
				
			})
		} ]
	}, /*{
		title : "日常事务管理",
		items : [ {
			xtype : "treepanel",
			id : 'dailymanage',
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border : 0,
			store : Ext.create("Ext.data.TreeStore", {
				defaultRootId : 'dailymanage', // 默认的根节点id
				model : "core.app.model.TreeModel",
				proxy : {
					type : "ajax", // 获取方式
					url : "login/login!getMenu.action" // 获取树节点的地址
				},
				clearOnLoad : false,
				nodeParam : "node"// 设置传递给后台的参数名,值是树节点的id属性
				
			})
		} ]
	}, {
		title : "查询与报表",
		items : [ {
			xtype : "treepanel",
			id : 'searchandreport',
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border : 0,
			store : Ext.create("Ext.data.TreeStore", {
				defaultRootId : 'searchandreport', // 默认的根节点id
				model : "core.app.model.TreeModel",
				proxy : {
					type : "ajax", // 获取方式
					url : "login/login!getMenu.action" // 获取树节点的地址
				},
				clearOnLoad : false,
				nodeParam : "node"// 设置传递给后台的参数名,值是树节点的id属性
				
			})
		} ]
	}, */{
		title : "系统管理",
		items : [ {
			xtype : "treepanel",
			id : 'systemmanage',
			rootVisible : false,// 不展示根节点
			displayField : "text",
			border : 0,
			store : Ext.create("Ext.data.TreeStore", {
				defaultRootId : 'systemmanage', // 默认的根节点id
				model : "core.app.model.TreeModel",
				proxy : {
					type : "ajax", // 获取方式
					url : "getMenu" // 获取树节点的地址
				},
				clearOnLoad : false,
				nodeParam : "node"// 设置传递给后台的参数名,值是树节点的id属性
				
			})
		} ]
	} ],
	initComponent : function() {
		this.callParent(arguments);
	}
});
