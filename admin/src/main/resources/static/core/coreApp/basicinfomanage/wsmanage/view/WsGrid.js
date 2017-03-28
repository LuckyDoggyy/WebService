Ext.define("core.basicinfomanage.wsmanage.view.WsGrid", {
	extend : "Ext.grid.Panel",
	alias : "widget.wsgrid",
	title : "<center height=40>服务浏览</center>",
	multiSelect : true,
	columnLines : true,
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
			xtype : 'textfield',
			fieldLabel : '服务编号',
			name : 'sid',
			labelWidth : 65
		}, {
		    xtype : 'textfield',
			fieldLabel : '服务名称',
			name : 'serviceName',
            labelWidth : 65
		}, {
			xtype : 'button',
			text : '查询',
			iconCls : 'search',
			ref : 'searchWs'
		}]
	}],

	columns : [{
				text : "服务id",
				dataIndex : "sid",
				align: 'center',
				width : 60
			}, {
				text : "服务名",
				dataIndex : "serviceName",
				align: 'center',
				width : 120
			}, {
				text : "请求地址",
				dataIndex : "url",
				align: 'center',
				width : 200
			}, {
				text : "命名空间",
				dataIndex : "targetNamespace",
				align: 'center',
				width : 200
			}, {
				text : "请求方法",
				dataIndex : "method",
				align: 'center',
				width : 150
			},{
				text : "备注",
				dataIndex : "remark",
				align: 'center',
				width : 120
			},{
				  xtype:'actioncolumn',
				  width:80,
				  text: '请求参数',
				  align: 'center',
				  items: [{
				  	  action: 'viewParam',
					  icon: 'core/css/imgs/form/view.png',
					  tooltip: '查看请求参数',
					  handler: function(grid, rowIndex, colIndex, node) {
						  var rec = grid.getStore().getAt(rowIndex);
						  this.fireEvent('itemclick', rec, node);
					  }
				  }]
			  },{
					 xtype:'actioncolumn',
					 width:80,
					 text: '调用',
					 align: 'center',
					 items: [{
					 	 action: 'call',
						 icon: 'core/css/imgs/wf/take.png',
						 tooltip: '调用服务',
						 handler: function(grid, rowIndex, colIndex, node) {
							 var rec = grid.getStore().getAt(rowIndex);
							 this.fireEvent('itemclick', rec, node);
						 }
					 }]
				 }],
	store : "core.basicinfomanage.wsmanage.store.WsStore",
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
				store : "core.basicinfomanage.wsmanage.store.WsStore",
				displayInfo : true,
				flex : 1
			}]

});