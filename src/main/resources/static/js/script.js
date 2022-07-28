let counterArray = [];

async function loadCounters() {    
    const response = await fetch('/counters');

    if (response.status !== 200) {
        console.log(`getting counter list error: ${response.status}`);
        return;
    }

    const counters = await response.json();
    
    const cardsParent = document.getElementById('cards');
    counterArray = [];

    let index = 0;

    counters.forEach(counter => {        
        index++;        
        counterArray.push(counter.id);
        
        cardsParent.innerHTML += `
        <div id="card_${index}" class="card m-2" style="width: 200px;"> 
            <div class="card-body">
                <h5 class="card-title">ID: ${counter.id}</h5>    
                <h5 id="value_${index}" class="card-title">Value: ${counter.value}</h5>
                <p id="title_$index" class="card-text">Title: ${counter.title || 'no title'}</p>
                <div class="input-group input-group-sm mb-3 w-100">
                    <div class="input-group-prepend">
                        <span class="input-group-text">Increment</span>
                    </div>
                    <input id="input_${index}" type="text" class="form-control" aria-label="Increment" aria-describedby="inputGroup-sizing-sm">
                </div>
                <button id="button_${index}" class="btn btn-primary w-100">Increase counter</button>
            </div>
        </div>`;    
    });

    cardsParent.querySelectorAll('button').forEach(button => {
        button.onclick = e => handleCounterIncrease(e);
    });
}

async function handleCounterIncrease(event) {    
    const index = parseInt(event.target.id.replace('button_', ''));
    const incrementCount = parseInt(document.getElementById(`input_${index}`).value);

    if (!incrementCount || incrementCount < 0) {
        const input = document.getElementById(`input_${index}`);
        input.classList.toggle('border-danger');
        setTimeout(() => {
            input.classList.toggle('border-danger');
        }, 1000);
        return;
    }

    const counterId = counterArray[index - 1];
    
    const response = await fetch('/counters', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            counterId: counterId,
            incrementCount: incrementCount
        })
    });

    if (response.status !== 200) {
        console.log(`adding value to counter error: ${response.status}`);
        return;
    }

    const newValue = await response.text();

    const valueElement = document.getElementById(`value_${index}`);
    valueElement.innerHTML = `Value: ${newValue}`;
}


loadCounters();  