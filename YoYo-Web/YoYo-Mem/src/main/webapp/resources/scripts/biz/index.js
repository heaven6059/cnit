// JavaScript Document

	$(function(){		
		var timer = null;
		var num = 0;
		var fnTimer = function(){
			num++;
			if(num == 4){
				num = 0;
			}
			//banner轮播图
			$('.banner_t ol li').eq(num).addClass('current').siblings().removeClass('current');
			$('.banner_t ul li').eq(num).stop().fadeIn().siblings().hide();
		};
		//第二种切换
		$('.banner_t ol li').mouseover(function(e) {
            $(this).addClass('current').siblings().removeClass('current');
			$('.banner_t ul li').eq($(this).index()).stop().fadeIn().siblings().hide();
			num = $(this).index();
		})
		//自动切换
		timer= setInterval(fnTimer,4000);
		
		$('.banner_t').mouseover(function(e) {
            clearInterval(timer);
        }).mouseout(function(e) {
			clearInterval(timer);
            timer = setInterval(fnTimer,4000);
        });
		
		
		
		
		//侧边栏滚动
		$(window).scroll(function(e) {
            var sT = $(window).scrollTop() + 14;
//			console.log(sT);
			$('.mui').stop().animate({'top': + sT + 'px'},40);
        });
		
		//返回顶部
		$('.mui ul .last').click(function(e) {
            //alert(1);
			$('html,body').animate({'scrollTop':'0px'},0);
        });
		
		$('#login-achor').on('click',function(){
			window.location.href = yoyo.memUrl+'/register/login?ReturnURL='+yoyo.urlEncode(window.location.href);
		})
		
		
})

