Ext.form.field.Base.prototype.initComponent = function() {
	var me = this;
	Ext.form.field.Base.superclass.initComponent.call(this);

	me.subTplData = me.subTplData || {};

	this.addEvents('focus', 'blur', 'specialkey');
	// Init mixins
	me.initLabelable();
	me.initField();

	// Default name to inputId
	if (!me.name) {
		me.name = me.getInputId();
	}
	if (this.allowBlank === false && this.fieldLabel) {
		this.fieldLabel += '<font color=red>*</font>';
	}

};

// this.fieldLabel){

