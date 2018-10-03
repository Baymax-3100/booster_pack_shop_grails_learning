<meta name="layout" content="main"/>

<script>
    window.onload = function () {
        var ctx = document.getElementById("report");
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ["Contact", "Contact Group", "Number"],
                datasets: [{
                    backgroundColor: ["rgba(255, 99, 132, 0.2)", "rgba(255, 159, 64, 0.2)", "rgba(153, 102, 255, 0.2)"],
                    label: "Report",
                    data: [${report.contact}, ${report.group}, ${report.number}],
                    options: {"scales": {"yAxes": [{"ticks": {"beginAtZero": true}}]}}
                }]
            }
        })
    }
</script>

<div class="card">
    <div class="card-header">
        <span class="float-right">
            <div class="btn-group">
                <g:link action="create" controller="contact" class="btn btn-success">New Contact</g:link>
                <g:link action="create" controller="contactGroup" class="btn btn-secondary">New Contact Group</g:link>
                <g:link action="index" controller="dashboard" class="btn btn-primary">Reload</g:link>
            </div>
        </span>
    </div>

    <div class="card-body">
        <div style="width: 600px">
            <canvas id="report"></canvas>
        </div>

    </div>
</div>