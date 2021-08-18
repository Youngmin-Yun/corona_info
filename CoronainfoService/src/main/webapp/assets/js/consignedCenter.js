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
                var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
                    mapOption = {
                        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                        level: 3 // 지도의 확대 레벨
                    };

                // 지도를 생성합니다    
                var map = new kakao.maps.Map(mapContainer, mapOption);

                // 주소-좌표 변환 객체를 생성합니다
                var geocoder = new kakao.maps.services.Geocoder();
                for(let i = 0; i<r.List.length; i++){
                // 주소로 좌표를 검색합니다
                geocoder.addressSearch(r.List[i].orgZipaddr, function (result, status) {

                    // 정상적으로 검색이 완료됐으면 
                    if (status === kakao.maps.services.Status.OK) {

                        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                        // 결과값으로 받은 위치를 마커로 표시합니다
                        var marker = new kakao.maps.Marker({
                            map: map,
                            position: coords
                        });

                        // 인포윈도우로 장소에 대한 설명을 표시합니다
                        var infowindow = new kakao.maps.InfoWindow({
                            content: '<div style="width:200px;height:20px;text-align:center;padding:6px 0;">'+r.List[i].orgnm+'</div>'+
                            '<div>'+"전화번호 : "+r.List[i].orgTlno+'</div>'+'<div>'+"진료 시작 : "+r.List[i].sttTm+'</div>'+'<div>'+"진료 마감 : "+r.List[i].endTm+'</div>'
                        });
                        // infowindow.open(map, marker);
                        kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
						kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
                        // 마커 이미지의 이미지 주소입니다
					function makeOverListener(map, marker, infowindow) {
						return function () {
							infowindow.open(map, marker);
						};
					}
					// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
					function makeOutListener(infowindow) {
						return function () {
							infowindow.close();
						};
					}
                        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                        map.setCenter(coords);
                    }
                });
            }
            }
        })
    }
})