Ext.define('User', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'sUuid', type: 'int' },
        { name: 'userName', type: 'string' },
        { name: 'password', type: 'string' },
        { name: 'initPass', type: 'string' },
        { name: 'userCode', type: 'int' },
        { name: 'createTime', type: 'string' },
        { name: 'initStatus', type: 'int' },
        { name: 'status', type: 'int' }
    ]
});

var store = Ext.create('Ext.data.Store', {
     model: 'User',
     proxy: {
         type: 'ajax',
         url: root + 'user?userData',
         reader: {			 
			root : 'records',
			totalProperty : 'totalCount',
			paramNames : {
				start : 'page.start',
				limit : 'page.limit'
			},
			remoteSort:true
         }
     },
     autoLoad: true
});

var sm = new Ext.selection.CheckboxModel(false);
 
var columns = {
	userColumns: [{
		xtype: "rownumberer", 
		text: "序号", 
		width:50,
		align:'center'
	},{ 
		text: "用户名", 
		dataIndex: "userName",
		flex: 1,
		align:'center'
	},{ 
		text: "初始密码", 
		dataIndex: "initPass",
		flex: 1,
		align:'center'
	},{ 
		text: "用户编号", 
		dataIndex: "userCode",
		flex: 1,
		align:'center'
	},{ 
		text: "创建时间", 
		dataIndex: "createTime",
		flex: 1,
		renderer: function(v, md, rd, rowIdx, colIdx, store){
			if(v == null){
				return "-";
			}else{
				return v;
			}
		}
	},{ 
		text: "状态", 
		dataIndex: "status", 
		flex: 1,
		align:'center',
      	renderer:function(v, md, rd, rowIdx, colIdx, store){
			if(v == 1){
				return '<font color="red">已禁用</font>';
			}else if(v == 2){
				return '<font color="green">已启用</font>';
			}
			return "-";
      	}
    }]
 }
 
 var formItems = {
 	userFormItems: [{
 		id:'form-userName',
        xtype: 'textfield',
        fieldLabel: '用户名',
        name: 'userName',
        allowBlank: true,
        blankText: '用户名不能为空'
 	},{
 		id:'form-initPass',
 		xtype: 'textfield',
        fieldLabel: '初始密码',
        name: 'initPass',
        allowBlank: true,
        blankText:'密码不能为空'
 	},{
 		id:'form-userCode',
 		xtype: 'numberfield',
        fieldLabel: '用户编号',
        name: 'userCode',
        allowBlank: true,
        blankText: '用户编号不能为空'
 	},{
 		xtype: 'combo',
	 	fieldLabel : '是否初始化账户',
		//hiddenName : 'initStatus',
		name:'initStatus',
		store : [[1,"是"],[2,"否"]],
		value : 2,
		mode : 'local',
		id :'mvc-user-initStatus',
		emptyText : '请选择',
		triggerAction: 'all',
		forceSelection : true,
		readOnly : false,
		editable : true
 	},{
 		xtype: 'combo',
	 	fieldLabel : '状态',
		//hiddenName : 'status',
		name:'status',
		store : [[1,"已禁用"],[2,"已启用"]],
		value : 2,
		mode : 'local',
		id :'mvc-user-status',
		emptyText : '请选择',
		triggerAction: 'all',
		forceSelection : true,
		readOnly : false,
		editable : true,
		allowBlank: true,
		blankText:'状态不能为空'
 	}]
 }
 
 var form = Ext.create('Ext.form.Panel', {
    items: formItems.userFormItems,
    bodyPadding: 10,
    url:"",
    buttons: [{
        text: '确认',
        handler: function() {
        	var userName = Ext.getCmp('form-userName').getValue();
        	if(userName==""){
        		Ext.Msg.alert('提示', '用户名不能为空');
        		return false;
        	}
        	var initPass = Ext.getCmp('form-initPass').getValue();
        	if(initPass==""){
        		Ext.Msg.alert('提示', '初始密码不能为空');
        		return false;
        	}
        	var userCode = Ext.getCmp('form-userCode').getValue();
        	if(userCode==""){
        		Ext.Msg.alert('提示', '用户编号不能为空');
        		return false;
        	}
            if (form.isValid()) {
                form.submit({
                	url: form.url,
                    success: function(form, action) {
                       store.reload();
                       Ext.Msg.alert('成功', action.result.message);
                       formWin.hide();
                    },
                    failure: function(form, action) {
                        Ext.Msg.alert('错误', action.result ? action.result.message : '没有返回结果');
                    }
                });
            }
        }
    },{
    	text: '取消',
    	handler: function() {
    		form.reset();
    		formWin.hide();
    	}
    }]
});

var formWin = Ext.create('Ext.window.Window', {
    title: '添加/编辑用户',
    height: 250,
    width: 350,
    layout: 'fit',
    items: form
});