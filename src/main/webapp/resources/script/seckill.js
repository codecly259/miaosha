/**
 * Created by maxinchun on 2016/6/15.
 */
// 存放主要交互逻辑js代码
// javascript 模块化
// seckill.detail.init(params);
var seckill = {
    // 封装秒杀相关ajax的url
    URL: {
        now: function () {
            return "/seckill/time/now ";
        }
    },
    handleSeckillkill: function () {
        // 处理秒杀逻辑
    },
    // 验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length === 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },
    countdown: function (seckillId, nowTime, startTime, endTime) {
        var seckillBox = $("#seckill-box");
        // 时间判断
        if (nowTime > endTime) {
            seckillBox.html("秒杀结束");
        } else if (nowTime < startTime) {
            // 秒杀未开始 ，计时事件绑定
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                var format = event.strftime("秒杀计时: %D天 %H时 %M分 %S秒");
                seckillBox.html(format);
                /* 时间完成后回调事件 */
            }).on("finish.countdown", function () {
                // 获取秒杀地址， 控制显示逻辑， 执行秒杀

            });
        } else {
            // 秒杀开始
            seckill.handleSeckillkill();
        }
    },
    // 详情页秒杀逻辑
    detail: {
        // 详情页初始化
        init: function (params) {
            // 手机验证和登录，计时交互
            // 规划我们的交互流程
            // 在cookie中查找手机号
            var killPhone = $.cookie('killPhone');
            // 验证手机号
            if (!seckill.validatePhone(killPhone)) {
                // 绑定phone
                // 控制输出
                var killPhoneModal = $("#killPhoneModal");
                // 显示弹出层
                // 显示弹出层
                killPhoneModal.modal({
                    show: true, // 显示弹出层
                    backdrop: 'static', // 禁止位置关闭
                    keybord: false, // 关闭键盘事件
                });
                $("#killPhoneBtn").click(function () {
                    var inputPhone = $("#killPhoneKey").val();
                    if (seckill.validatePhone(inputPhone)) {
                        // 电话写入cookie
                        $.cookie("killPhone", inputPhone, {expirse: 7, path: "/seckill"});
                        // 刷新页面
                        window.location.reload();
                    } else {
                        $("#killPhoneMessage").hide().html("<label class='label label-danger'>手机号错误！</label>").show(300);
                    }
                });
            }
            // 已经登录
            // 及时交互
            var startTime = params.startTime;
            var endTime = params.endTime;
            var seckillId = params.seckillId;
            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result.success) {
                    var nowTime = result.data;
                    console.log(nowTime);
                    // 时间判断
                    seckill.countdown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log("result:" + result);
                }
            });
        }
    }
};
