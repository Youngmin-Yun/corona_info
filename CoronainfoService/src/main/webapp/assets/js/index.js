$(function () {

    $.ajax({
        type: "get",
        url: "/api/coronaInfo/today",
        success: function (r) {
            console.log(r)
            $("#accExamCnt").html(r.data.strAccExamCnt)
            $("#decideCnt").html(r.data.strDecideCnt)
            let ctx2 = $("#confirmed_chart")
            let confirmed_chart = new Chart(ctx2, {
                type: 'pie',
                options: {
                    responsive: false
                },
                data: {
                    labels: ["양성", "음성"],
                    datasets: [{
                        label: "양성/음성",
                        data: [r.data.decideCnt, r.data.examCnt - r.data.decideCnt],
                        backgroundColor: ['green', 'blue', 'red']
                    }]
                },
            })
        }
    })
    $.ajax({
        type: "get",
        url: "/api/coronaSido/today",
        success: function (r) {
            console.log(r)
            let sidoName = new Array()
            let defCnt = new Array()

            for (let i = 0; i < 6; i++) {
                let tag = "<tbody class='region-tbody'></tbody>"
                $(".region_confirm_tbl").append(tag)
            }
            for (let i = 0; i < r.data.length; i++) {
                let sido = r.data[i].gubun
                let cnt = r.data[i].incDec
                sidoName.push(sido)
                defCnt.push(cnt)
                console.log(Math.floor(i / 3))
                let page = Math.floor(i / 3)
                let tag =
                    '<tr>' +
                    '<td>' + r.data[i].gubun + '</td>' +
                    '<td>' + r.data[i].defCnt + '</td>' +
                    '<td>' + r.data[i].incDec + ' ▲</td>' +
                    '</tr>'
                $(".region-tbody").eq(page).append(tag)
            }
            $(".region-tbody").eq(0).addClass("active")

            $("#region_next").click(function () {
                let currentPage = Number($(".current").html())
                currentPage++
                if (currentPage > 6) currentPage = 6;
                $(".current").html(currentPage)
                $(".region-tbody").removeClass("active")
                $(".region-tbody").eq(currentPage - 1).addClass("active")

            })
            $("#region_prev").click(function () {
                let currentPage = Number($(".current").html())
                currentPage--
                if (currentPage < 1) currentPage = 1;
                $(".current").html(currentPage)
                $(".region-tbody").removeClass("active")
                $(".region-tbody").eq(currentPage - 1).addClass("active")

            })
            let ctx = $("#regional_status")
            let regionalChart = new Chart(ctx, {
                type: 'bar',
                options: {
                    responsive: false
                },
                data: {
                    labels: sidoName,
                    datasets: [{
                        label: "2021-08-09 신규확진",
                        data: defCnt,
                        backgroundColor: ['green', 'blue', 'red']
                    }]
                }
            })
        }
    })
    $.ajax({
        type: "get",
        url: "/api/coronaAge/today",
        success: function (r) {
            console.log(r)
            let age = new Array();
            let conf = new Array();
            for (let i = 0; i < r.data.length; i++) {
                let years = r.data[i].gubun
                let confCase = r.data[i].confCase
                age.push(years)
                conf.push(confCase)
            }
            let ctx4 = $("#age_chart")
            let ageChart = new Chart(ctx4, {
                type: 'bar',
                options: {
                    responsive: false
                },
                data: {
                    labels: age,
                    datasets: [{
                        label: "2021-08-09 신규확진",
                        data: conf,
                        backgroundColor: ['green', 'blue', 'red']
                    }]
                }
            })
        }
    })
    $.ajax({
        type: "get",
        url: "/api/coronaGen/today",
        success: function (r) {
            console.log(r)
            // let gender = r.data.gubun
            let gender = new Array()
            let conf = new Array()
            // let confCase = r.data.confCase
            for (let i = 0; i < r.data.length; i++) {
                let gen = r.data[i].gubun
                let confCase = r.data[i].confCase
                gender.push(gen)
                conf.push(confCase)
            }
            let ctx5 = $("#gen_chart")
            let gender_chart = new Chart(ctx5, {
                type: 'pie',
                options: {
                    responsive: false
                },
                data: {
                    labels: gender,
                    datasets: [{
                        label: "양성/음성",
                        data: conf,
                        backgroundColor: ['blue', 'red']
                    }]
                }
            })
        }
    })
    let ctx3 = $("#vaccine_chart")
    let vaccineChart = new Chart(ctx3, {
        type: 'bar',
        options: {
            responsive: false
        },
        data: {
            labels: ["전국", "서울특별시", "부산광역시", "대구광역시", "광주광역시", "대전광역시", "인천광역시", "울산광역시",
                "세종특별자치시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도",
                "제주특별자치도"
            ],
            datasets: [{
                    label: "2021-08-09 1차 접종현황",
                    data: [415, 408, 86, 65, 123, 88, 30, 68, 24, 42, 39, 19, 25, 21, 14, 11, 1],
                    backgroundColor: ['green']
                },
                {
                    label: "2021-08-09 2차 접종현황",
                    data: [415, 408, 86, 65, 123, 88, 30, 68, 24, 42, 39, 19, 25, 21, 14, 11, 1],
                    backgroundColor: ['blue']
                }
            ]
        }
    })
})