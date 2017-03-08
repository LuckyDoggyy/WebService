Ext.define("core.basicinfomanage.peoplemanage.view.AddPeople",
				{
					extend : "Ext.form.Panel",
					alias : "widget.addpeople",
					title: "<center height=40>人员添加</center>",
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
							fieldLabel : '用户名'+ '<font color=red>*</font>',
							anchor: '18%',
							labelWidth: 50,
							name : 'account',
							allowBlank : false,
							blankText : '账号不能为空'
						},{
							xtype : 'textfield',
							fieldLabel : '昵称',
							anchor: '18%',
							labelWidth: 50,
							name : 'nickName',
							allowBlank : true
						},{
							xtype : 'textarea',
							fieldLabel : '备注',
							anchor: '18%',
							labelWidth: 50,
                            width: 230,
							name : 'remarks',
							allowBlank : true
						},{
							 xtype: 'button',
							 text: '添加',
							 ref : 'addPeople',
							 iconCls : "table_save"
						 }]
				});