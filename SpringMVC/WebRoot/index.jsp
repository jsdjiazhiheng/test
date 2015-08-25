<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>Extjs案例演示登录页</title>
	<jsp:include page="/inc/include.jsp" /> 
<script type="text/javascript">
Ext.onReady(function(){  
	//开启悬浮提示功能
	Ext.QuickTips.init();
	//开启TabPanel悬浮提示功能
    Ext.tip.QuickTipManager.init();
    
    Ext.define('com.view.form.LoginForm', {
	    extend: 'Ext.form.Panel',
	    xtype: 'form-login',
	    alias: 'widget.LoginForm',
	    title: '登录系统',
	    frame:true,
	    width: 320,
	    bodyPadding: 10,
	    
	    defaultType: 'textfield',
	    
	    items: [{
	        allowBlank: false,
	        fieldLabel: '用户名',
	        name: 'user',
	        labelWidth:70,
	        emptyText: '用户名'
	    }, {
	        allowBlank: false,
	        fieldLabel: '密　码',
	        name: 'password',
	        emptyText: '密码',
	        labelWidth:70,
	        inputType: 'password'
	    }],
	    
	    buttons: [
	        { text:'注册' },
	        { text:'登录' }
	    ],
	    
	    initComponent: function() {
	        this.defaults = {
	            anchor: '100%',
	            labelWidth: 120
	        };
	        
	        this.callParent();
	    }
	});
    
	
	var viewport = new Ext.Viewport({
		layout : {
			align: 'middle',
			pack: 'center',
			type: 'hbox'
		},
		defaults : {
			border : false
		},
		items: Ext.widget("LoginForm")
	});
});
</script>
  </head>
  <body>
  </body>
</html>
