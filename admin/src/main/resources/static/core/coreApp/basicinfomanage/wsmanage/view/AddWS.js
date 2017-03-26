Ext.define("core.basicinfomanage.wsmanage.view.AddWS",
				{
					extend : "Ext.form.Panel",
					alias : "widget.addws",
					title: "<center height=40>添加服务</center>",
					bodyPadding : '5 5 0',
					width : 150,
                    height: 300,
					buttonAlign : 'center',
					fieldDefaults : {
						labelAlign : 'left',
						msgTarget : 'side'
					},
					layout : 'anchor',
					items : [{
							xtype : 'textfield',
							fieldLabel : '服务名',
							anchor: '18%',
							labelWidth: 70,
							name : 'serviceName',
							allowBlank : false,
							blankText : '服务名不能为空'
						},{
							xtype : 'textfield',
							fieldLabel : '请求地址',
							anchor: '18%',
							labelWidth: 70,
							name : 'url',
							allowBlank : false
						},{
							xtype : 'textfield',
							fieldLabel : 'wsdl地址',
							anchor: '18%',
							labelWidth: 70,
							name : 'wsdlUrl',
							allowBlank : false
						},{
							xtype : 'textfield',
							fieldLabel : '命名空间',
							anchor: '18%',
							labelWidth: 70,
							name : 'targetNamespace',
							allowBlank : false
						},{
							xtype : 'textfield',
							fieldLabel : '请求方法',
							anchor: '18%',
							labelWidth: 70,
							name : 'method',
							allowBlank : false
						},{
							xtype : 'textarea',
							fieldLabel : '备注',
							anchor: '18%',
							labelWidth: 70,
                            width: 230,
							name : 'remark',
							allowBlank : true
						},{
							 xtype: 'button',
							 text: '添加',
							 ref : 'addWS',
							 iconCls : "table_save"
						 }]
				});