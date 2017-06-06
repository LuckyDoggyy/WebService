Ext.define("core.service.view.Weather",
				{
					extend : "Ext.panel.Panel",
					alias : "widget.weather",
					title: "<center height=40>天气查询</center>",
					bodyPadding : '5 5 0',
					buttonAlign : 'center',
					layout : 'anchor',
					autoScroll:true,
					html: '<iframe id="frame" src="serviceview/test.html" frameborder="0" width="100%" height="100%"></iframe>'
				});