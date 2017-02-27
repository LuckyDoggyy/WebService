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
							name : 'username',
							allowBlank : false,
							blankText : '姓名不能为空'
						},{
							 xtype: 'button',
							 text: '添加',
							 ref : 'addPeople',
							 iconCls : "table_save"
						 }]
				});