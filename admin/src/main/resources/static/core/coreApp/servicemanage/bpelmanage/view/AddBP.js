Ext.define("core.servicemanage.bpelmanage.view.AddBP",
				{
					extend : "Ext.panel.Panel",
					alias : "widget.addbp",
					title: "<center height=40>流程添加</center>",
					bodyPadding : '5 5 0',
					buttonAlign : 'center',
					layout : 'anchor',
					autoScroll:true,
					html: '<iframe id="frame" src="myflow/add.html" frameborder="0" width="100%" height="100%"></iframe>'
				});