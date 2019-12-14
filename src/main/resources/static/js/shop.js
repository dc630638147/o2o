
$(function () {
    var getAllShopCategory = '/app/shopCategory/getAll';
    var getAllArea = '/app/area/getAllArea';
    var codeUrl ='/shopApi/kaptcha';
    //更换验证码

    $('#kaptcha').click(function () {
        $('#kaptcha').attr('src',codeUrl+'?'+Math.floor(Math.random()*100));
    });

    function getShopInitInfo() {
        //商铺
        $.getJSON(getAllShopCategory, function (res) {
            if (res.status == 200) {
                var shopCategoryHtml = '';
                $.each(res.data, function (index, obj) {
                    var html = '<option value="' + obj.shopCategoryId + '">' + obj.shopCategoryName + '</option>';
                    shopCategoryHtml = shopCategoryHtml + html;
                });
                $('#shopCategoryId').html(shopCategoryHtml);
            }
        });

        //区域
        $.getJSON(getAllArea, function (res) {
            if (res.status == 200) {
                var areaHtml = '';
                $.each(res.data, function (index, obj) {
                    var html = '<option value="' + obj.areaId + '">' + obj.areaName + '</option>';
                    areaHtml = areaHtml + html;
                });
                $('#areaId').html(areaHtml);
            }
        });
    }

    //执行获取方法
    getShopInitInfo();

    //form表单提交
    $('#submit ').click(function () {
        var code = $('#code').val();
        if(code == '' || code == undefined){
            alert("请填写验证码");
            return
        }
        //上传图片
        var file = $('#file')[0].files[0];
        var shopCategoryId = $('#shopCategoryId').val();
        var shopName = $('#shopName').val();
        var areaId = $('#areaId').val();
        var phone = $('#phone').val();
        var shopDesc = $('#shopDesc').val();
        var fom = new FormData();
        fom.append('file',file);
        fom.append('shopCategoryId',shopCategoryId);
        fom.append('shopName',shopName);
        fom.append('areaId',areaId);
        fom.append('phone',phone);
        fom.append('code',code);
        fom.append('shopDesc',shopDesc);
        $.ajax({
            type: "POST",//方法类型
            url: "/shopApi/register",//url
            dataType:'json',
            data:fom,
            //async: false,
            contentType: false, //禁止设置请求类型
            processData: false, //禁止jquery对DAta数据的处理,默认会处理
            success: function (res) {
                if(res.status == 200){
                    alert("上传成功");
                }else{
                    alert(res.msg);
                }

            },
            error:function () {
                alert("服务器异常");
            }
        });
    });
    

})