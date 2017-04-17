Ext.define("core.servicemanage.bpelmanage.view.UpdateBPGrid", {
	extend : "Ext.grid.Panel",
	alias : "widget.updatebpgrid",
	title : "<center height=40>流程修改</center>",
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
			fieldLabel : '业务标识',
			name : 'flowid',
			labelWidth : 65
		}, {
		    xtype : 'textfield',
			fieldLabel : '业务名称',
			name : 'flowname',
            labelWidth : 65
		}, {
			xtype : 'button',
			text : '查询',
			iconCls : 'search',
			ref : 'searchBP'
		}]
	}],

	columns : [{
				text : "id",
				dataIndex : "autoid",
				align: 'center',
				width : 60
			}, {
				text : "业务标识",
				dataIndex : "flowid",
				align: 'center',
				width : 120
			}, {
				text : "业务名称",
				dataIndex : "flowname",
				align: 'center',
				width : 200
			}, {
				text : "详情",
				dataIndex : "description",
				align: 'center',
				width : 200
			},{
				text : "状态",
				dataIndex : "state",
				align: 'center',
				width : 200,
				renderer : function(value) {
					if (value == 0) {
						return "启用";
					}else if(value==1){
						return "停用";
					}
				}
			},{
				  xtype:'actioncolumn',
				  width:80,
				  text: '修改流程图',
				  align: 'center',
				  items: [{
					  action: 'flowupdate',
					  icon: 'core/css/imgs/form/view.png',
					  tooltip: '修改流程图',
					  handler: function(grid, rowIndex, colIndex, node) {
						  var rec = grid.getStore().getAt(rowIndex);
						  this.fireEvent('itemclick', rec, node);
					  }
				  }]
			  }],
	store : "core.servicemanage.bpelmanage.store.BPStore",
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
				store : "core.servicemanage.bpelmanage.store.BPStore",
				displayInfo : true,
				flex : 1
			}]
});