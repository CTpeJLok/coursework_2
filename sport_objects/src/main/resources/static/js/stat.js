function fillHist(canvas, table, column_index, split_char, dataset_name, title, y_title, x_title) {
    function getRandomInt(min, max) {
        return Math.floor(Math.random() * (max - min)) + min;
    }

    let labels = []
    let data = []
    let tr = table.getElementsByClassName('table-tr');
    for (let i = 0; i < tr.length; i++) {
        let t = tr[i].getElementsByClassName('table-th')[column_index].textContent.split(split_char)[0];

        if (!labels.includes(t)) {
            let ind = Math.max(labels.length, 0);
            labels[ind] = t;
            data[ind] = 1;
        } else {
            let ind = labels.indexOf(t);
            data[ind] += 1;
        }
    }

    let colors = [];
    for (let i = 0; i < labels.length; i++) {
        let temp = 'rgb(' + getRandomInt(0, 256) + ', ' + getRandomInt(0, 256) + ', ' + getRandomInt(0, 256) + ')';
        while (colors.includes(temp)) {
            temp = 'rgb(' + getRandomInt(0, 256) + ', ' + getRandomInt(0, 256) + ', ' + getRandomInt(0, 256) + ')';
        }

        colors[i] = temp;
    }

    let dataset = {
        label: dataset_name,
        data: data,
        backgroundColor: colors,
        borderWidth: 1,
    }

    Chart.defaults.global.defaultFontColor = 'white';

    let myChart = new Chart(canvas, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [dataset],
        },
        options: {
            legend: {
                display: false,
            },
            title: {
                display: true,
                text: title,
                position: 'top',
                fontSize: 24,
                padding: 20,
            },
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        stepSize: 1,
                    },
                    scaleLabel: {
                        display: true,
                        labelString: y_title,
                        fontSize: 18,
                    },
                }],
                xAxes: [{
                    scaleLabel: {
                        display: true,
                        labelString: x_title,
                        fontSize: 18,
                    },
                }],
            },
        },
    });
}