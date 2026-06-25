
function calcularTotal() {

    let total = 0;

    const checkboxes = document.querySelectorAll('input[name="servicos"]:checked');

    checkboxes.forEach(cb => {
        total += parseFloat(cb.value);
    });

    document.getElementById("total").innerText = total.toFixed(2);
    document.getElementById("totalInput").innerText = total.toFixed(2);//Total
    console.log(total.toFixed(2));
}
