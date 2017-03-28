Ext.define("core.basicinfomanage.wsmanage.view.ShowWsParamsGrid", {
			extend : "Ext.grid.Panel",
			alias : "widget.showwsparamsgrid",
			title : "<center height=40>服务参数</center>",
			multiSelect : true,
			columnLines : true,
			loadMask : {
				msg : "数据加载中，请稍等..."
			},
			autoScroll : true,
			disableSelection : false,
			enableKeyNav : true,
			columns : [{
						text : "参数名",
						dataIndex : "paramName",
						align: 'center',
						width : 150
					}, {
						text : "备注",
						dataIndex : "remark",
						align: 'center',
						width : 150
					}],
			store : "core.basicinfomanage.wsmanage.store.WsParamStore"
		});