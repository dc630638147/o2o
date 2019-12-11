
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
c    });
    

})