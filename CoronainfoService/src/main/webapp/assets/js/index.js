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
            let date = new Array()
            for (let i = 0; i < 6; i++) {
                let tag = "<tbody class='region-tbody'></tbody>"
                $(".region_confirm_tbl").append(tag)
            }
            for (let i = 0; i < r.data.length; i++) {
                let sido = r.data[i].gubun
                let cnt = r.data[i].incDec
                let dt = r.data[i].dt
                sidoName.push(sido)
                defCnt.push(cnt)
                date.push(dt)
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
                        label: date[0] + " 기준 지역별신규확진",
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
            let dt = new Array();
            for (let i = 0; i < r.data.length; i++) {
                let years = r.data[i].ages
                let confCase = r.data[i].confCase
                let day = r.data[i].dt
                age.push(years)
                conf.push(confCase)
                dt.push(day)
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
                        label: dt[0] + " 기준 연령대별신규확진",
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
            let gender = new Array()
            let conf = new Array()
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
                        label: "남/녀",
                        data: conf,
                        backgroundColor: ['blue', 'red']
                    }]
                }
            })
        }
    })
    $.ajax({
        type: "get",
        url: "/api/coronaVaccine/today",
        success: function (r) {
            console.log(r)
            let sido = new Array()
            let first = new Array()
            // let accFirst = new Array()
            let second = new Array()
            // let accSecond = new Array()
            let date = new Array()
            for(let i = 0; i<r.data.length; i++){
                let city = r.data[i].region
                let frt = r.data[i].firstCnt
                let sec = r.data[i].secondCnt
                let dt = r.data[i].dt
                // accFirst += r.data[i].accFirstCnt
                // accSecond += r.data[i].accSecondCnt
                sido.push(city)
                first.push(frt)
                second.push(sec)
                date.push(dt)
            }
            let ctx3 = $("#vaccine_chart")
            let vaccineChart = new Chart(ctx3, {
                type: 'bar',
                options: {
                    responsive: false
                },
                data: {
                    labels: sido,
                    datasets: [{
                            label: date[0]+" 1차 접종현황",
                            data: first,
                            backgroundColor: ['green']
                        },
                        {
                            label: date[0]+" 2차 접종현황",
                            data: second,
                            backgroundColor: ['blue']
                        }
                    ]
                }
            })
        }
    })
})