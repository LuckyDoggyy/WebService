Ext.define("core.basicinfomanage.menumanage.view.UpdateMenuGrid", {
	extend : "Ext.grid.Panel",
	alias : "widget.updatemenugrid",
	title : "<center height=40>菜单修改</center>",
	selModel : {
		selType : "checkboxmodel",
		mode : 'SIMPLE'
	},
	multiSelect : true,
	columnLines : true, // 展示竖线
	loadMask : {
		msg : "数据加载中，请稍等..."
	},
	autoScroll : true,
	disableSelection : false,
	enableKeyNav : true,
	dockedItems : [{
		xtype : 'toolbar',
		dock : 'top',
		items : [{
			fieldLabel : '菜单名称',
			xtype : "combobox",
			name : 'mid',
			labelWidth : 75,
			emptyText : '请选择菜单',
			queryMode : 'remote',
			store : "core.basicinfomanage.menumanage.store.MenuNodeStore",
			loadingText : '正在加载数据，请稍侯……',
			triggerAction : 'all',
			valueField : 'mid',
			forceSelection : false,
			displayField : 'menuname'
		},{
			xtype : 'button',
			text : '查询',
			iconCls : 'search',
			ref : 'searchMenu'
		}]
	}, {
		xtype : 'toolbar',
		dock : 'top',
		height : 36,
		items : [{
					xtype : 'button',
					text : '修改菜单',
					ref : 'updateMenu',
					iconCls : 'modify',
					disabled : true
				}]
	}],

	columns : [{
				text : "菜单编号",
				dataIndex : "mid",
				align: 'center',
				width : 150
			}, {
				text : "菜单名",
				dataIndex : "menuname",
				align: 'center',
				width : 120
			}, {
				text : "父菜单编号",
				dataIndex : "pid",
				align: 'center',
				width : 180
			}, {
				text : "父菜单名",
				dataIndex : "pname",
				align: 'center',
				width : 120
			}],
	store : "core.basicinfomanage.menumanage.store.MenuStore",
	bbar : [{
				xtype : 'button',
				text : '全选',
				handler : function(button, e) {
					button.ownerCt.ownerCt.getSelectionModel().selectAll();
				}
			}, {
				xtype : 'button',
				text : '取消',
				handler : function(button, e) {
					button.ownerCt.ownerCt.getSelectionModel().deselectAll();
				}
			}, {
				xtype : 'pagingtoolbar',
				store : "core.basicinfomanage.menumanage.store.MenuStore",
				displayInfo : true,
				flex : 1
			}]

});