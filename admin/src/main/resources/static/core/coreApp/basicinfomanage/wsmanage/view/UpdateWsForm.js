Ext.define("core.basicinfomanage.wsmanage.view.UpdateWsForm", {
			extend : "Ext.form.Panel",
			alias : "widget.updatewsform",
			mixins : {
				suppleUtil : "core.util.SuppleUtil"
			},
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},
			align : 'center',
			buttonAlign : 'center',
			items : [ {
				xtype : "textfield",
				fieldLabel : "工号" + '<font color=red>*</font>',
				labelWidth : 35,
				padding : '5,20,5,30',
				name : "usergh",
				allowBlank : false,// 不允许为空
				blankText : '工号不能为空',// 错误提示内容
				emptyText : '请输入新班组人员的工号',
				readOnly : false
			}, {
				xtype : "textfield",
				fieldLabel : "姓名",
				labelWidth : 35,
				padding : '5,20,5,30',
				name : "username",
				disabled:true,
				allowBlank : false,
				readOnly : false
			},{
				xtype : "textfield",
				fieldLabel : "部门编号",
				labelWidth : 35,
				padding : '5,20,5,30',
				name : "deptbh",
				allowBlank : false,
				hidden : true
			},{
				xtype : "textfield",
				fieldLabel : "部门",
				labelWidth : 35,
				padding : '5,20,5,30',
				name : "deptname",
				disabled:true,
				allowBlank : false,
				readOnly : false
			},{
				xtype : "textfield",
				fieldLabel : "班组名",
				labelWidth : 90,
				padding : '5,20,5,30',
				name : "banzuname",
				allowBlank : false,// 允许为空
				hidden : "true",
				readOnly : false
			},{ xtype : 'textfield',
				name : 'remarks',
				fieldLabel : '备注',
				labelWidth :35,
				padding : '5,20,5,30',
				allowBlank : true,
			}],			
			buttons : [{
						text : '修改',
						ref : 'updateBanzuPeople',
						iconCls : "table_save"
					}, {
						text : '返回',
						ref : "return",
						iconCls : "return"
						,
					}]
		});