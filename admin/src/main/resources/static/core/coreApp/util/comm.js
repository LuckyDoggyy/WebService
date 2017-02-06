var comm = Ext.create("Ext.util.MixedCollection");
var coreApp = null;
/** 表单必填项样式 */
comm.add('required',
		'<span style="color:red;font-weight:bold" data-qtip="必填项">*</span>');