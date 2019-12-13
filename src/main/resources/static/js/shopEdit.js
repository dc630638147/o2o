
$(function () {
   // aleer('aa');
    var codeUrl ='/shopApi/kaptcha';
    //根据shopId获取信息
    $('#kaptcha').click(function () {
        $('#kaptcha').attr('src',codeUrl+'?'+Math.floor(Math.random()*100));
    });
    var shopId = $("#shopId").val();
    var shopInfoUrl = '/shopApi/allShopInfo';
    //获取数据
    $.getJSON(shopInfoUrl+"/"+shopId,function (res) {
        console.log(res);
        if(res.status == 200){
            var data = res.data;
            var shop = data.shop;
            var shopCategoryList = data.shopCategoryList;
            var areaList = data.areaList;
            //商铺名
            $("#shopName").val(shop.shopName);
            //分类
            var shopCategoryId = shop.shopCategoryId;
            var shopCategoryHtml = '';
            $.each(shopCategoryList, function (index, obj) {
                if(obj.shopCategoryId == shopCategoryId){
                    var html = '<option selected value="' + obj.shopCategoryId + '">' + obj.shopCategoryName + '</option>';
                    shopCategoryHtml = shopCategoryHtml + html;
                }else{
                    var html = '<option value="' + obj.shopCategoryId + '">' + obj.shopCategoryName + '</option>';
                    shopCategoryHtml = shopCategoryHtml + html;
                }
            });
            $('#shopCategoryId').html(shopCategoryHtml);

            //区域
            var areaId = shop.areaId;
            var areaHtml = '';
            $.each(areaList, function (index, obj) {
                if(obj.areaId == areaId){
                    var html = '<option selected value="' + obj.areaId + '">' + obj.areaName + '</option>';
                    areaHtml = areaHtml + html;
                }else{
                    var html = '<option value="' + obj.areaId + '">' + obj.areaName + '</option>';
                    areaHtml = areaHtml + html;
                }
            });
            $('#areaId').html(areaHtml);

            //联系电话
            $('#phone').val(shop.phone);
            $('#shopAddr').val(shop.shopAddr);
            $('#shopDesc').val(shop.shopDesc);
        }else{
            alert(res.msg);
        }
    })

    //form表单提交
    $('#submit ').click(function () {
        var code = $('#code').val();
        if(code == '' || code == undefined){
            alert("请填写验证码");
            return
        }
        //上传图片
        var file = $('#file')[0].files[0];
        var areaId = $('#areaId').val();
        var phone = $('#phone').val();
        var shopDesc = $('#shopDesc').val();
        var fom = new FormData();
        if(file !=null && file!=undefined){
            fom.append('file',file);
        }
        fom.append("shopId",shopId);
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
                    alert("更新成功");
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