Ext.define("core.servicemanage.bpelmanage.view.BPView",
				{
					extend : "Ext.form.Panel",
					alias : "widget.bpview",
					title: "<center height=40>流程查看</center>",
					bodyPadding : '5 5 0',
					width : 150,
                    height: 300,
					buttonAlign : 'center',
					fieldDefaults : {
						labelAlign : 'left',
						msgTarget : 'side'
					},
					layout : 'anchor',
					contentEl: "frame1"
				});