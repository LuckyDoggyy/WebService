Ext.define("core.basicinfomanage.wsmanage.view.AddWsForm", {
			extend : "Ext.form.Panel",
			alias : "widget.addwsform",
			mixins : {
				suppleUtil : "core.util.SuppleUtil"
			},
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},
			align : 'center',
			buttonAlign : 'center',
			items : [{
				xtype : "textfield",
				fieldLabel : "服务名",
				labelWidth : 45,
				padding : '5,20,5,30',
				name : "name",
				allowBlank : false
			},{
				xtype : "textfield",
				fieldLabel : "ip",
				labelWidth : 35,
				padding : '5,20,5,30',
				name : "ip",
				allowBlank : false
			},{
				xtype : "textfield",
				fieldLabel : "端口",
				labelWidth : 35,
				padding : '5,20,5,30',
				name : "port",
				allowBlank : false
			},{ xtype : 'textfield',
				name : 'detail',
				fieldLabel : '详情',
				labelWidth : 35,
				padding : '5,20,5,30',
				allowBlank : true
			}],
			buttons : [{
						text : '添加',
						ref : 'addWs',
						iconCls : "table_save",
						disabled :"true"
					}, {
						text : '返回',
						ref : "return",
						iconCls : "return"
						
					}]
		});