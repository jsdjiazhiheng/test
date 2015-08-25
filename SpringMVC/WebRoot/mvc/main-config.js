function logout(){
	alert("logout")
}

Ext.onReady(function(){   
	
	
	//开启悬浮提示功能
    Ext.QuickTips.init();
    //开启TabPanel悬浮提示功能
    Ext.tip.QuickTipManager.init();
    //开启动态加载
    Ext.Loader.setConfig({
        enabled: true
    }); 
    		
	var viewport = new Ext.Viewport({
		layout : 'border',
		border : true,
		defaults : {
			border : true
		},
		items : [{
			//title: '这是顶部信息',
	        region: 'north',
	        xtype: 'panel',
	        height: 50,
	        //split: true,
	        layout: 'fit',
	        html:'<div class="app-header white-color">' +
	        	 '   <div class="app-header-logo"></div><span class="app-header-logo-text">管理系统</span>' +
	        	 '   <div class="app-header-text">' +
	        	 '        <span>当前登录用户为：' + SESSION_USER +'</span>  ' +
	        	 '        <a href="javascript:logout();">注销登录</a>' +
	        	 '   </div>' +
	        	 '</div>'
		},{
	        title: '<i class="icon-reorder white-color"></i> 导航菜单',
	        region:'west',
	        xtype: 'panel',
	        width: 200,
	        collapsible: true,   
	        id: 'west-region-container',
	        layout: 'fit',
	        items: Ext.create('MVC.view.layout.Accordion')
		},{
			title: '主页',
	        region: 'center',     
	        xtype: 'panel',
	        id:'contentWrapper',
	        layout: 'fit',
	        html: 'conter'
		}]
	});
});

