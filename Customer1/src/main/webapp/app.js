
document.addEventListener("DOMContentLoaded", function() {
    fetchCustomers();
});

function fetchCustomers() {
    fetch('customers?cmd=get_customer_list&page=0&size=10&sortField=first_name&sortDirection=asc&searchQuery=')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#customerTable tbody");
            tableBody.innerHTML = '';
            data.forEach(customer => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${customer.uuid}</td>
                    <td>${customer.first_name}</td>
                    <td>${customer.last_name}</td>
                    <td>${customer.street}</td>
                    <td>${customer.address}</td>
                    <td>${customer.city}</td>
                    <td>${customer.state}</td>
                    <td>${customer.email}</td>
                    <td>${customer.phone}</td>
                    <td>
                        <button onclick="editCustomer('${customer.uuid}')">Edit</button>
                        <button onclick="deleteCustomer('${customer.uuid}')">Delete</button>
                    </td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching customers:', error));
}

function editCustomer(uuid) {
    // Redirect to edit form with the customer's UUID
    window.location.href = `customer-form.jsp?uuid=${uuid}`;
}

function deleteCustomer(uuid) {
    fetch(`customers?uuid=${uuid}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.status === 204) {
            fetchCustomers();
        } else {
            console.error('Error deleting customer:', response);
        }
    });
}
/**
 * 
 */