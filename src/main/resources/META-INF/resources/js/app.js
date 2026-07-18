
function calcularTotal() {

    let total = 0;

    const checkboxes = document.querySelectorAll('input[name="servicos"]:checked');

    checkboxes.forEach(cb => {
        total += parseFloat(cb.dataset.cost);
    });

    document.getElementById("total").innerText = total.toFixed(2);
    document.getElementById("totalInput").value = total.toFixed(2);//Total
    console.log(total.toFixed(2));
}

function confirmBarber()
{
    const select = document.querySelector('select[name="barbeiro"]');
    const barber = select.value;

    console.log(barber);
}

function dateSelected()
{
    //const dateSelect = document.querySelector('select[name="serviceTime"]');

    //const date = dateSelect.value;

    //console.log(date);
}
