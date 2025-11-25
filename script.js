// ============================================
// STORAGE MANAGEMENT
// ============================================

// Initialize localStorage with sample data if empty
function initializeData() {
    if (!localStorage.getItem('users')) {
        const sampleUsers = [
            { id: '1', name: 'Pedro Gomez', document: 'CC-123456', role: 'DOCTOR' },
            { id: '2', name: 'Pablo Cortes', document: 'CC-123478', role: 'NURSE' },
            { id: '3', name: 'Sofia Castillo', document: 'CC-876432', role: 'ADMIN' }
        ];
        localStorage.setItem('users', JSON.stringify(sampleUsers));
    }

    if (!localStorage.getItem('providers')) {
        const sampleProviders = [
            { id: '1', name: 'Meditech SAS', nit: 'NIT-900123456', email: 'contacto@meditech.com' },
            { id: '2', name: 'Leonardo', nit: 'NIT-900676876', email: 'contacto@leonardo.com' },
            { id: '3', name: 'Dana', nit: 'NIT-086547689', email: 'contacto@dana.com' }
        ];
        localStorage.setItem('providers', JSON.stringify(sampleProviders));
    }

    if (!localStorage.getItem('biomedical')) {
        const sampleBiomedical = [
            {
                id: '1',
                serial: 'BIO-001',
                brand: 'Philips',
                model: 'MX450',
                type: 'MEDICAL',
                state: 'IN_USE',
                provider: '1',
                riskClass: 'Class IIb',
                calibrationCert: 'CERT-BIO-001-2024',
                image: 'https://images.unsplash.com/photo-1581594693702-fbdc51b2763b'
            },
            {
                id: '2',
                serial: 'BIO-005',
                brand: 'Acer',
                model: 'MX315',
                type: 'MEDICAL',
                state: 'IN_STORAGE',
                provider: '2',
                riskClass: 'Class IIa',
                calibrationCert: 'CERT-BIO-005-2024',
                image: 'https://images.unsplash.com/photo-1530026405186-ed1f139313f8'
            }
        ];
        localStorage.setItem('biomedical', JSON.stringify(sampleBiomedical));
    }

    if (!localStorage.getItem('tech')) {
        const sampleTech = [
            {
                id: '1',
                serial: 'BIO-002',
                brand: 'HP',
                model: 'MX510',
                type: 'COMPUTING',
                state: 'NEW',
                provider: '1',
                os: 'Windows 11 Pro',
                ram: '16',
                image: 'https://images.unsplash.com/photo-1588872657578-7efd1f1555ed'
            },
            {
                id: '2',
                serial: 'BIO-003',
                brand: 'Dell',
                model: 'MX642',
                type: 'OFFICE',
                state: 'IN_USE',
                provider: '2',
                os: 'Ubuntu 22.04 LTS',
                ram: '32',
                image: 'https://images.unsplash.com/photo-1587202372634-32705e3bf49c'
            }
        ];
        localStorage.setItem('tech', JSON.stringify(sampleTech));
    }
}

// Get data from localStorage
function getData(key) {
    const data = localStorage.getItem(key);
    return data ? JSON.parse(data) : [];
}

// Save data to localStorage
function saveData(key, data) {
    localStorage.setItem(key, JSON.stringify(data));
}

// Generate unique ID
function generateId(data) {
    if (data.length === 0) return '1';
    const maxId = Math.max(...data.map(item => parseInt(item.id)));
    return (maxId + 1).toString();
}

// ============================================
// NAVIGATION
// ============================================

document.addEventListener('DOMContentLoaded', function() {
    initializeData();
    
    // Navigation
    const navItems = document.querySelectorAll('.nav-item');
    navItems.forEach(item => {
        item.addEventListener('click', function(e) {
            e.preventDefault();
            
            // Remove active class from all items
            navItems.forEach(nav => nav.classList.remove('active'));
            
            // Add active class to clicked item
            this.classList.add('active');
            
            // Get tab name
            const tabName = this.dataset.tab;
            
            // Hide all tabs
            document.querySelectorAll('.tab-content').forEach(tab => {
                tab.classList.remove('active');
            });
            
            // Show selected tab
            document.getElementById(`${tabName}-tab`).classList.add('active');
            
            // Update page title
            const titles = {
                'dashboard': 'Panel Principal',
                'users': 'Gestión de Usuarios',
                'providers': 'Proveedores',
                'biomedical': 'Equipos Biomédicos',
                'tech': 'Equipos Tecnológicos'
            };
            document.getElementById('page-title').textContent = titles[tabName];
            
            // Load data for the tab
            loadTabData(tabName);
        });
    });

    // Quick actions
    document.querySelectorAll('.action-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const action = this.dataset.action;
            // Navigate to the tab
            document.querySelector(`[data-tab="${action}"]`).click();
            // Open modal after a short delay
            setTimeout(() => {
                switch(action) {
                    case 'users':
                        openUserModal();
                        break;
                    case 'providers':
                        openProviderModal();
                        break;
                    case 'biomedical':
                        openBiomedicalModal();
                        break;
                    case 'tech':
                        openTechModal();
                        break;
                }
            }, 300);
        });
    });

    // Load initial data
    loadTabData('dashboard');
    
    // Search functionality
    setupSearch();
    
    // Form submissions
    setupForms();
});

function loadTabData(tabName) {
    switch(tabName) {
        case 'dashboard':
            loadDashboardStats();
            break;
        case 'users':
            loadUsers();
            break;
        case 'providers':
            loadProviders();
            break;
        case 'biomedical':
            loadBiomedical();
            break;
        case 'tech':
            loadTech();
            break;
    }
}

// ============================================
// DASHBOARD
// ============================================

function loadDashboardStats() {
    const users = getData('users');
    const providers = getData('providers');
    const biomedical = getData('biomedical');
    const tech = getData('tech');

    document.getElementById('total-users').textContent = users.length;
    document.getElementById('total-providers').textContent = providers.length;
    document.getElementById('total-biomedical').textContent = biomedical.length;
    document.getElementById('total-tech').textContent = tech.length;
}

// ============================================
// USERS
// ============================================

function loadUsers() {
    const users = getData('users');
    const tbody = document.getElementById('users-table-body');
    
    tbody.innerHTML = users.map(user => `
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.document}</td>
            <td>${getRoleLabel(user.role)}</td>
            <td class="action-buttons">
                <button class="btn btn-edit" onclick="editUser('${user.id}')">
                    <i class="fas fa-edit"></i> Editar
                </button>
                <button class="btn btn-danger" onclick="deleteUser('${user.id}')">
                    <i class="fas fa-trash"></i> Eliminar
                </button>
            </td>
        </tr>
    `).join('');
}

function getRoleLabel(role) {
    const roles = {
        'ADMIN': 'Administrador',
        'DOCTOR': 'Doctor',
        'NURSE': 'Enfermera',
        'SECRETARY': 'Secretaria',
        'BOSS': 'Jefe',
        'WATCHMAN': 'Vigilante',
        'MAINTENANCE_MAIN': 'Mantenimiento'
    };
    return roles[role] || role;
}

function openUserModal(userId = null) {
    const modal = document.getElementById('user-modal');
    const form = document.getElementById('user-form');
    const title = document.getElementById('user-modal-title');
    
    form.reset();
    
    if (userId) {
        const users = getData('users');
        const user = users.find(u => u.id === userId);
        if (user) {
            title.textContent = 'Editar Usuario';
            document.getElementById('user-id').value = user.id;
            document.getElementById('user-name').value = user.name;
            document.getElementById('user-document').value = user.document;
            document.getElementById('user-role').value = user.role;
        }
    } else {
        title.textContent = 'Agregar Usuario';
    }
    
    modal.classList.add('active');
}

function closeUserModal() {
    document.getElementById('user-modal').classList.remove('active');
}

function editUser(userId) {
    openUserModal(userId);
}

function deleteUser(userId) {
    if (confirm('¿Estás seguro de eliminar este usuario?')) {
        let users = getData('users');
        users = users.filter(u => u.id !== userId);
        saveData('users', users);
        loadUsers();
        showToast('Usuario eliminado correctamente', 'success');
    }
}

// ============================================
// PROVIDERS
// ============================================

function loadProviders() {
    const providers = getData('providers');
    const tbody = document.getElementById('providers-table-body');
    
    tbody.innerHTML = providers.map(provider => `
        <tr>
            <td>${provider.id}</td>
            <td>${provider.name}</td>
            <td>${provider.nit}</td>
            <td>${provider.email}</td>
            <td class="action-buttons">
                <button class="btn btn-edit" onclick="editProvider('${provider.id}')">
                    <i class="fas fa-edit"></i> Editar
                </button>
                <button class="btn btn-danger" onclick="deleteProvider('${provider.id}')">
                    <i class="fas fa-trash"></i> Eliminar
                </button>
            </td>
        </tr>
    `).join('');
}

function openProviderModal(providerId = null) {
    const modal = document.getElementById('provider-modal');
    const form = document.getElementById('provider-form');
    const title = document.getElementById('provider-modal-title');
    
    form.reset();
    
    if (providerId) {
        const providers = getData('providers');
        const provider = providers.find(p => p.id === providerId);
        if (provider) {
            title.textContent = 'Editar Proveedor';
            document.getElementById('provider-id').value = provider.id;
            document.getElementById('provider-name').value = provider.name;
            document.getElementById('provider-nit').value = provider.nit;
            document.getElementById('provider-email').value = provider.email;
        }
    } else {
        title.textContent = 'Agregar Proveedor';
    }
    
    modal.classList.add('active');
}

function closeProviderModal() {
    document.getElementById('provider-modal').classList.remove('active');
}

function editProvider(providerId) {
    openProviderModal(providerId);
}

function deleteProvider(providerId) {
    if (confirm('¿Estás seguro de eliminar este proveedor?')) {
        let providers = getData('providers');
        providers = providers.filter(p => p.id !== providerId);
        saveData('providers', providers);
        loadProviders();
        showToast('Proveedor eliminado correctamente', 'success');
    }
}

// ============================================
// BIOMEDICAL EQUIPMENT
// ============================================

function loadBiomedical() {
    const biomedical = getData('biomedical');
    const providers = getData('providers');
    const grid = document.getElementById('biomedical-grid');
    
    if (biomedical.length === 0) {
        grid.innerHTML = '<p style="color: var(--text-secondary); text-align: center; width: 100%;">No hay equipos biomédicos registrados</p>';
        return;
    }
    
    grid.innerHTML = biomedical.map(equip => {
        const provider = providers.find(p => p.id === equip.provider);
        return `
            <div class="equipment-card">
                <img src="${equip.image || 'https://via.placeholder.com/400x200?text=Sin+Imagen'}" 
                     alt="${equip.brand} ${equip.model}" 
                     class="equipment-image"
                     onerror="this.src='https://via.placeholder.com/400x200?text=Sin+Imagen'">
                <div class="equipment-info">
                    <div class="equipment-header">
                        <div>
                            <h3>${equip.brand} ${equip.model}</h3>
                            <p>${equip.serial}</p>
                        </div>
                        <span class="status-badge ${equip.state.toLowerCase()}">${getStateLabel(equip.state)}</span>
                    </div>
                    <div class="equipment-details">
                        <div class="detail-row">
                            <span>Tipo:</span>
                            <span>${getTypeLabel(equip.type)}</span>
                        </div>
                        <div class="detail-row">
                            <span>Proveedor:</span>
                            <span>${provider ? provider.name : 'N/A'}</span>
                        </div>
                        <div class="detail-row">
                            <span>Clase de Riesgo:</span>
                            <span>${equip.riskClass}</span>
                        </div>
                        <div class="detail-row">
                            <span>Certificado:</span>
                            <span>${equip.calibrationCert}</span>
                        </div>
                    </div>
                    <div class="equipment-actions">
                        <button class="btn btn-edit" onclick="editBiomedical('${equip.id}')">
                            <i class="fas fa-edit"></i> Editar
                        </button>
                        <button class="btn btn-danger" onclick="deleteBiomedical('${equip.id}')">
                            <i class="fas fa-trash"></i> Eliminar
                        </button>
                    </div>
                </div>
            </div>
        `;
    }).join('');
}

function openBiomedicalModal(equipmentId = null) {
    const modal = document.getElementById('biomedical-modal');
    const form = document.getElementById('biomedical-form');
    const title = document.getElementById('biomedical-modal-title');
    
    // Load providers in select
    const providers = getData('providers');
    const providerSelect = document.getElementById('biomedical-provider');
    providerSelect.innerHTML = '<option value="">Seleccionar proveedor</option>' +
        providers.map(p => `<option value="${p.id}">${p.name}</option>`).join('');
    
    form.reset();
    
    if (equipmentId) {
        const biomedical = getData('biomedical');
        const equip = biomedical.find(e => e.id === equipmentId);
        if (equip) {
            title.textContent = 'Editar Equipo Biomédico';
            document.getElementById('biomedical-id').value = equip.id;
            document.getElementById('biomedical-serial').value = equip.serial;
            document.getElementById('biomedical-brand').value = equip.brand;
            document.getElementById('biomedical-model').value = equip.model;
            document.getElementById('biomedical-type').value = equip.type;
            document.getElementById('biomedical-state').value = equip.state;
            document.getElementById('biomedical-provider').value = equip.provider;
            document.getElementById('biomedical-risk').value = equip.riskClass;
            document.getElementById('biomedical-cert').value = equip.calibrationCert;
            document.getElementById('biomedical-image').value = equip.image || '';
        }
    } else {
        title.textContent = 'Agregar Equipo Biomédico';
    }
    
    modal.classList.add('active');
}

function closeBiomedicalModal() {
    document.getElementById('biomedical-modal').classList.remove('active');
}

function editBiomedical(equipmentId) {
    openBiomedicalModal(equipmentId);
}

function deleteBiomedical(equipmentId) {
    if (confirm('¿Estás seguro de eliminar este equipo?')) {
        let biomedical = getData('biomedical');
        biomedical = biomedical.filter(e => e.id !== equipmentId);
        saveData('biomedical', biomedical);
        loadBiomedical();
        showToast('Equipo eliminado correctamente', 'success');
    }
}

// ============================================
// TECH EQUIPMENT
// ============================================

function loadTech() {
    const tech = getData('tech');
    const providers = getData('providers');
    const grid = document.getElementById('tech-grid');
    
    if (tech.length === 0) {
        grid.innerHTML = '<p style="color: var(--text-secondary); text-align: center; width: 100%;">No hay equipos tecnológicos registrados</p>';
        return;
    }
    
    grid.innerHTML = tech.map(equip => {
        const provider = providers.find(p => p.id === equip.provider);
        return `
            <div class="equipment-card">
                <img src="${equip.image || 'https://via.placeholder.com/400x200?text=Sin+Imagen'}" 
                     alt="${equip.brand} ${equip.model}" 
                     class="equipment-image"
                     onerror="this.src='https://via.placeholder.com/400x200?text=Sin+Imagen'">
                <div class="equipment-info">
                    <div class="equipment-header">
                        <div>
                            <h3>${equip.brand} ${equip.model}</h3>
                            <p>${equip.serial}</p>
                        </div>
                        <span class="status-badge ${equip.state.toLowerCase()}">${getStateLabel(equip.state)}</span>
                    </div>
                    <div class="equipment-details">
                        <div class="detail-row">
                            <span>Tipo:</span>
                            <span>${getTypeLabel(equip.type)}</span>
                        </div>
                        <div class="detail-row">
                            <span>Proveedor:</span>
                            <span>${provider ? provider.name : 'N/A'}</span>
                        </div>
                        <div class="detail-row">
                            <span>Sistema Operativo:</span>
                            <span>${equip.os}</span>
                        </div>
                        <div class="detail-row">
                            <span>RAM:</span>
                            <span>${equip.ram} GB</span>
                        </div>
                    </div>
                    <div class="equipment-actions">
                        <button class="btn btn-edit" onclick="editTech('${equip.id}')">
                            <i class="fas fa-edit"></i> Editar
                        </button>
                        <button class="btn btn-danger" onclick="deleteTech('${equip.id}')">
                            <i class="fas fa-trash"></i> Eliminar
                        </button>
                    </div>
                </div>
            </div>
        `;
    }).join('');
}

function openTechModal(equipmentId = null) {
    const modal = document.getElementById('tech-modal');
    const form = document.getElementById('tech-form');
    const title = document.getElementById('tech-modal-title');
    
    // Load providers in select
    const providers = getData('providers');
    const providerSelect = document.getElementById('tech-provider');
    providerSelect.innerHTML = '<option value="">Seleccionar proveedor</option>' +
        providers.map(p => `<option value="${p.id}">${p.name}</option>`).join('');
    
    form.reset();
    
    if (equipmentId) {
        const tech = getData('tech');
        const equip = tech.find(e => e.id === equipmentId);
        if (equip) {
            title.textContent = 'Editar Equipo Tecnológico';
            document.getElementById('tech-id').value = equip.id;
            document.getElementById('tech-serial').value = equip.serial;
            document.getElementById('tech-brand').value = equip.brand;
            document.getElementById('tech-model').value = equip.model;
            document.getElementById('tech-type').value = equip.type;
            document.getElementById('tech-state').value = equip.state;
            document.getElementById('tech-provider').value = equip.provider;
            document.getElementById('tech-os').value = equip.os;
            document.getElementById('tech-ram').value = equip.ram;
            document.getElementById('tech-image').value = equip.image || '';
        }
    } else {
        title.textContent = 'Agregar Equipo Tecnológico';
    }
    
    modal.classList.add('active');
}

function closeTechModal() {
    document.getElementById('tech-modal').classList.remove('active');
}

function editTech(equipmentId) {
    openTechModal(equipmentId);
}

function deleteTech(equipmentId) {
    if (confirm('¿Estás seguro de eliminar este equipo?')) {
        let tech = getData('tech');
        tech = tech.filter(e => e.id !== equipmentId);
        saveData('tech', tech);
        loadTech();
        showToast('Equipo eliminado correctamente', 'success');
    }
}

// ============================================
// HELPER FUNCTIONS
// ============================================

function getStateLabel(state) {
    const states = {
        'NEW': 'Nuevo',
        'IN_USE': 'En Uso',
        'UNDER_MAINTENANCE': 'En Mantenimiento',
        'DAMAGED': 'Dañado',
        'IN_STORAGE': 'En Almacenamiento',
        'LOST': 'Perdido',
        'DECOMMISSIONED': 'Dado de Baja',
        'RESERVED': 'Reservado',
        'PENDING_INSPECTION': 'Pendiente de Inspección',
        'REPLACEMENT_NEEDED': 'Requiere Reemplazo',
        'RECOVERED': 'Recuperado',
        'DISPOSED': 'Desechado'
    };
    return states[state] || state;
}

function getTypeLabel(type) {
    const types = {
        'COMPUTING': 'Computación',
        'MEDICAL': 'Médico',
        'LABORATORY': 'Laboratorio',
        'AUDIOVISUAL': 'Audiovisual',
        'OFFICE': 'Oficina',
        'INFRASTRUCTURE': 'Infraestructura',
        'OTHER': 'Otro'
    };
    return types[type] || type;
}

// ============================================
// FORMS
// ============================================

function setupForms() {
    // User Form
    document.getElementById('user-form').addEventListener('submit', function(e) {
        e.preventDefault();
        
        const userId = document.getElementById('user-id').value;
        const user = {
            id: userId || generateId(getData('users')),
            name: document.getElementById('user-name').value,
            document: document.getElementById('user-document').value,
            role: document.getElementById('user-role').value
        };
        
        let users = getData('users');
        if (userId) {
            users = users.map(u => u.id === userId ? user : u);
            showToast('Usuario actualizado correctamente', 'success');
        } else {
            users.push(user);
            showToast('Usuario agregado correctamente', 'success');
        }
        
        saveData('users', users);
        loadUsers();
        closeUserModal();
    });

    // Provider Form
    document.getElementById('provider-form').addEventListener('submit', function(e) {
        e.preventDefault();
        
        const providerId = document.getElementById('provider-id').value;
        const provider = {
            id: providerId || generateId(getData('providers')),
            name: document.getElementById('provider-name').value,
            nit: document.getElementById('provider-nit').value,
            email: document.getElementById('provider-email').value
        };
        
        let providers = getData('providers');
        if (providerId) {
            providers = providers.map(p => p.id === providerId ? provider : p);
            showToast('Proveedor actualizado correctamente', 'success');
        } else {
            providers.push(provider);
            showToast('Proveedor agregado correctamente', 'success');
        }
        
        saveData('providers', providers);
        loadProviders();
        closeProviderModal();
    });

    // Biomedical Form
    document.getElementById('biomedical-form').addEventListener('submit', function(e) {
        e.preventDefault();
        
        const equipmentId = document.getElementById('biomedical-id').value;
        const equipment = {
            id: equipmentId || generateId(getData('biomedical')),
            serial: document.getElementById('biomedical-serial').value,
            brand: document.getElementById('biomedical-brand').value,
            model: document.getElementById('biomedical-model').value,
            type: document.getElementById('biomedical-type').value,
            state: document.getElementById('biomedical-state').value,
            provider: document.getElementById('biomedical-provider').value,
            riskClass: document.getElementById('biomedical-risk').value,
            calibrationCert: document.getElementById('biomedical-cert').value,
            image: document.getElementById('biomedical-image').value
        };
        
        let biomedical = getData('biomedical');
        if (equipmentId) {
            biomedical = biomedical.map(e => e.id === equipmentId ? equipment : e);
            showToast('Equipo actualizado correctamente', 'success');
        } else {
            biomedical.push(equipment);
            showToast('Equipo agregado correctamente', 'success');
        }
        
        saveData('biomedical', biomedical);
        loadBiomedical();
        closeBiomedicalModal();
    });

    // Tech Form
    document.getElementById('tech-form').addEventListener('submit', function(e) {
        e.preventDefault();
        
        const equipmentId = document.getElementById('tech-id').value;
        const equipment = {
            id: equipmentId || generateId(getData('tech')),
            serial: document.getElementById('tech-serial').value,
            brand: document.getElementById('tech-brand').value,
            model: document.getElementById('tech-model').value,
            type: document.getElementById('tech-type').value,
            state: document.getElementById('tech-state').value,
            provider: document.getElementById('tech-provider').value,
            os: document.getElementById('tech-os').value,
            ram: document.getElementById('tech-ram').value,
            image: document.getElementById('tech-image').value
        };
        
        let tech = getData('tech');
        if (equipmentId) {
            tech = tech.map(e => e.id === equipmentId ? equipment : e);
            showToast('Equipo actualizado correctamente', 'success');
        } else {
            tech.push(equipment);
            showToast('Equipo agregado correctamente', 'success');
        }
        
        saveData('tech', tech);
        loadTech();
        closeTechModal();
    });
}

// ============================================
// SEARCH
// ============================================

function setupSearch() {
    // Users search
    document.getElementById('search-users').addEventListener('input', function(e) {
        const searchTerm = e.target.value.toLowerCase();
        const users = getData('users');
        const filtered = users.filter(user => 
            user.name.toLowerCase().includes(searchTerm) ||
            user.document.toLowerCase().includes(searchTerm) ||
            user.role.toLowerCase().includes(searchTerm)
        );
        
        const tbody = document.getElementById('users-table-body');
        tbody.innerHTML = filtered.map(user => `
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.document}</td>
                <td>${getRoleLabel(user.role)}</td>
                <td class="action-buttons">
                    <button class="btn btn-edit" onclick="editUser('${user.id}')">
                        <i class="fas fa-edit"></i> Editar
                    </button>
                    <button class="btn btn-danger" onclick="deleteUser('${user.id}')">
                        <i class="fas fa-trash"></i> Eliminar
                    </button>
                </td>
            </tr>
        `).join('');
    });

    // Providers search
    document.getElementById('search-providers').addEventListener('input', function(e) {
        const searchTerm = e.target.value.toLowerCase();
        const providers = getData('providers');
        const filtered = providers.filter(provider => 
            provider.name.toLowerCase().includes(searchTerm) ||
            provider.nit.toLowerCase().includes(searchTerm) ||
            provider.email.toLowerCase().includes(searchTerm)
        );
        
        const tbody = document.getElementById('providers-table-body');
        tbody.innerHTML = filtered.map(provider => `
            <tr>
                <td>${provider.id}</td>
                <td>${provider.name}</td>
                <td>${provider.nit}</td>
                <td>${provider.email}</td>
                <td class="action-buttons">
                    <button class="btn btn-edit" onclick="editProvider('${provider.id}')">
                        <i class="fas fa-edit"></i> Editar
                    </button>
                    <button class="btn btn-danger" onclick="deleteProvider('${provider.id}')">
                        <i class="fas fa-trash"></i> Eliminar
                    </button>
                </td>
            </tr>
        `).join('');
    });
}

// ============================================
// TOAST NOTIFICATIONS
// ============================================

function showToast(message, type = 'success') {
    const toast = document.getElementById('toast');
    toast.textContent = message;
    toast.className = `toast ${type} show`;
    
    setTimeout(() => {
        toast.classList.remove('show');
    }, 3000);
}

// Close modals when clicking outside
window.addEventListener('click', function(e) {
    if (e.target.classList.contains('modal')) {
        e.target.classList.remove('active');
    }
});
