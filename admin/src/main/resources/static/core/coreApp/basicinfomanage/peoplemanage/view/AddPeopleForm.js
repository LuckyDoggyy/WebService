Ext.define("core.basicinfomanage.peoplemanage.view.AddPeopleForm",
				{
					extend : "Ext.form.Panel",
					alias : "widget.addpeopleform",
					bodyPadding : '5 5 0',
					width : 600,
					buttonAlign : 'center',
					fieldDefaults : {
						labelAlign : 'top',
						msgTarget : 'side'
					},
					defaults : {
						border : false,
						xtype : 'panel',
						flex : 1,
						layout : 'anchor'
					},
					layout : 'vbox',
					items : [
							{
								items : [
										{
											xtype : 'textfield',
											fieldLabel : '用户名'+ '<font color=red>*</font>',
											anchor : '-5',
											name : 'username',
											allowBlank : false,
											blankText : '姓名不能为空'
										}]
							} ],
					buttons : [ {
						text : '添加',
						ref : 'addPeople',
						iconCls : "table_save"
					}, {
						text : '返回',
						ref : "return",
						iconCls : "return"
					} ]
				});