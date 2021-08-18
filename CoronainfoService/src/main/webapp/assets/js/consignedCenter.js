$(function () {
    $("#region_select").change(function () {
        let region = $(this).find("option:selected").val()
        getConsignedCenter(region)
        console.log(region)
    })
    getConsignedCenter("서울특별시")

    function getConsignedCenter(region) {
        $(".consignedCenter_location").html(region)
        $.ajax({
            type: "get",
            url: "/api/corona/vaccineCenter/consigned/adr?adr=" + region,
            success: function (r) {
                console.log(r)
                
            }
        })
    }
})