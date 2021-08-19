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
                for (let i = 0; i < r.List.length; i++) {
                    var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
                        mapOption = {
                            center: new kakao.maps.LatLng(r.List[0].lat, r.List[0].lng), // 지도의 중심좌표
                            level: 3 // 지도의 확대 레벨
                        };
                    console.log(r.List[0].lat, r.List[0].lng)
                }

                var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
                for (let i = 0; i < r.List.length; i++) {
                    
                    // 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다 
                    var positions = [{
                        content: '<div style="width:200px;height:20px;text-align:center;padding:6px 0;">' + r.List[i].orgnm + '</div>' +
                            '<div>' + "전화번호 : " + r.List[i].orgTlno + '</div>' + 
                            '<div>' + "진료 시작 : " + r.List[i].sttTm + '</div>' + 
                            '<div>' + "진료 마감 : " + r.List[i].endTm + '</div>' +
                            '<div>' + "주소 : " + r.List[i].orgZipaddr + '</div>',
                        latlng: new kakao.maps.LatLng(r.List[i].lat, r.List[i].lng)
                    }];

                    for (var k = 0; k < positions.length; k++) {
                        // 마커를 생성합니다
                        var marker = new kakao.maps.Marker({
                            map: map, // 마커를 표시할 지도
                            position: positions[k].latlng // 마커의 위치
                        });

                        // 마커에 표시할 인포윈도우를 생성합니다 
                        var infowindow = new kakao.maps.InfoWindow({
                            content: positions[k].content // 인포윈도우에 표시할 내용
                        });

                        // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
                        // 이벤트 리스너로는 클로저를 만들어 등록합니다 
                        // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
                        kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
                        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
                    }

                    // 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
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
                }
            }
        })
    }
})