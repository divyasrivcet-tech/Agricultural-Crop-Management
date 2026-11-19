const modules = {
  farmers: {
    title: 'Farmer Management', singular: 'Farmer', endpoint: '/api/farmers', number: 'MODULE 01',
    fields: [
      {key:'farmerName',label:'Farmer Name',type:'text',required:true,source:'farmer_name'},
      {key:'phone',label:'Phone Number',type:'tel',required:true,source:'phone'},
      {key:'village',label:'Village',type:'text',required:true,source:'village'},
      {key:'district',label:'District',type:'text',required:true,source:'district'},
      {key:'address',label:'Full Address',type:'text',required:false,source:'address'}],
    columns:[['farmer_id','ID'],['farmer_name','Farmer Name'],['phone','Phone'],['village','Village'],['district','District'],['address','Address']]
  },
  fields: {
    title:'Field Management',singular:'Field',endpoint:'/api/fields',number:'MODULE 02',
    fields:[{key:'farmerId',label:'Farmer',type:'select',required:true,from:'/api/farmers',id:'farmer_id',display:'farmer_name',source:'farmer_id'},
      {key:'fieldName',label:'Field Name',type:'text',required:true,source:'field_name'},
      {key:'areaAcres',label:'Area (acres)',type:'number',required:true,step:'0.01',min:'0.01',source:'area_acres'},
      {key:'location',label:'Field Location',type:'text',required:false,source:'location'}],
    columns:[['field_id','ID'],['farmer_name','Farmer'],['field_name','Field Name'],['area_acres','Area (acres)'],['location','Location']]
  },
  crops: {
    title:'Crop Management',singular:'Crop',endpoint:'/api/crops',number:'MODULE 03',
    fields:[{key:'cropName',label:'Crop Name',type:'text',required:true,source:'crop_name'},
      {key:'cropType',label:'Crop Type',type:'text',required:true,source:'crop_type'}],
    columns:[['crop_id','ID'],['crop_name','Crop Name'],['crop_type','Crop Type']]
  },
  seasons: {
    title:'Season Management',singular:'Season',endpoint:'/api/seasons',number:'MODULE 04',
    fields:[{key:'seasonName',label:'Season Name',type:'text',required:true,source:'season_name'},
      {key:'startDate',label:'Start Date',type:'date',required:true,source:'start_date'},
      {key:'endDate',label:'End Date',type:'date',required:true,source:'end_date'}],
    columns:[['season_id','ID'],['season_name','Season'],['start_date','Start Date'],['end_date','End Date']]
  },
  'yield-records': {
    title:'Yield Record Management',singular:'Yield Record',endpoint:'/api/yield-records',number:'MODULE 05',
    fields:[{key:'fieldId',label:'Field',type:'select',required:true,from:'/api/fields',id:'field_id',display:'field_name',source:'field_id'},
      {key:'cropId',label:'Crop',type:'select',required:true,from:'/api/crops',id:'crop_id',display:'crop_name',source:'crop_id'},
      {key:'seasonId',label:'Season',type:'select',required:true,from:'/api/seasons',id:'season_id',display:'season_name',source:'season_id'},
      {key:'yieldKg',label:'Yield (kg)',type:'number',required:true,step:'0.01',min:'0',source:'yield_kg'}],
    columns:[['record_id','ID'],['field_name','Field'],['crop_name','Crop'],['season_name','Season'],['yield_kg','Yield (kg)']]
  },
  cultivation: {
    title:'Cultivation Activity Management',singular:'Cultivation Activity',endpoint:'/api/cultivation',number:'MODULE 06',
    fields:[
      {key:'fieldId',label:'Field',type:'select',required:true,from:'/api/fields',id:'field_id',display:'field_name',source:'field_id'},
      {key:'cropId',label:'Crop',type:'select',required:true,from:'/api/crops',id:'crop_id',display:'crop_name',source:'crop_id'},
      {key:'activityName',label:'Activity Name',type:'text',required:true,source:'activity_name'},
      {key:'activityDate',label:'Activity Date',type:'date',required:true,source:'activity_date'},
      {key:'description',label:'Description',type:'text',required:false,source:'description'}],
    columns:[['activity_id','ID'],['field_id','Field ID'],['crop_id','Crop ID'],['activity_name','Activity'],['activity_date','Date'],['description','Description']]
  },
  fertilizers: {
    title:'Fertilizer and Input Management',singular:'Fertilizer Input',endpoint:'/api/fertilizers',number:'MODULE 07',
    fields:[
      {key:'fieldId',label:'Field',type:'select',required:true,from:'/api/fields',id:'field_id',display:'field_name',source:'field_id'},
      {key:'cropId',label:'Crop',type:'select',required:true,from:'/api/crops',id:'crop_id',display:'crop_name',source:'crop_id'},
      {key:'inputName',label:'Input Name',type:'text',required:true,source:'input_name'},
      {key:'quantity',label:'Quantity',type:'number',required:true,step:'0.01',min:'0',source:'quantity'},
      {key:'unit',label:'Unit',type:'text',required:false,source:'unit'},
      {key:'cost',label:'Cost',type:'number',required:true,step:'0.01',min:'0',source:'cost'},
      {key:'inputDate',label:'Input Date',type:'date',required:true,source:'input_date'}],
    columns:[['input_id','ID'],['field_id','Field ID'],['crop_id','Crop ID'],['input_name','Input'],['quantity','Quantity'],['unit','Unit'],['cost','Cost'],['input_date','Date']]
  },
  irrigation: {
    title:'Irrigation Management',singular:'Irrigation Record',endpoint:'/api/irrigation',number:'MODULE 08',
    fields:[
      {key:'fieldId',label:'Field',type:'select',required:true,from:'/api/fields',id:'field_id',display:'field_name',source:'field_id'},
      {key:'irrigationDate',label:'Irrigation Date',type:'date',required:true,source:'irrigation_date'},
      {key:'waterQuantity',label:'Water Quantity',type:'number',required:false,step:'0.01',min:'0',source:'water_quantity'},
      {key:'durationMinutes',label:'Duration (minutes)',type:'number',required:false,min:'0',source:'duration_minutes'},
      {key:'cost',label:'Cost',type:'number',required:true,step:'0.01',min:'0',source:'cost'},
      {key:'notes',label:'Notes',type:'text',required:false,source:'notes'}],
    columns:[['irrigation_id','ID'],['field_id','Field ID'],['irrigation_date','Date'],['water_quantity','Water'],['duration_minutes','Duration (min)'],['cost','Cost'],['notes','Notes']]
  },
  harvests: {
    title:'Harvest Record Management',singular:'Harvest Record',endpoint:'/api/harvests',number:'MODULE 09',
    fields:[
      {key:'fieldId',label:'Field',type:'select',required:true,from:'/api/fields',id:'field_id',display:'field_name',source:'field_id'},
      {key:'cropId',label:'Crop',type:'select',required:true,from:'/api/crops',id:'crop_id',display:'crop_name',source:'crop_id'},
      {key:'seasonId',label:'Season',type:'select',required:false,from:'/api/seasons',id:'season_id',display:'season_name',source:'season_id'},
      {key:'harvestDate',label:'Harvest Date',type:'date',required:true,source:'harvest_date'},
      {key:'quantityKg',label:'Harvest Quantity (kg)',type:'number',required:true,step:'0.01',min:'0',source:'quantity_kg'},
      {key:'sellingPricePerKg',label:'Selling Price per kg',type:'number',required:true,step:'0.01',min:'0',source:'selling_price_per_kg'},
      {key:'otherCost',label:'Other Cost',type:'number',required:true,step:'0.01',min:'0',source:'other_cost'}],
    columns:[['harvest_id','ID'],['field_id','Field ID'],['crop_id','Crop ID'],['season_id','Season ID'],['harvest_date','Date'],['quantity_kg','Quantity (kg)'],['selling_price_per_kg','Price/kg'],['other_cost','Other Cost']]
  },
};
let activeModule = 'farmers';
const $ = id => document.getElementById(id);
const form = $('recordForm');

async function api(url, options={}) {
  const response = await fetch(url, options);
  const type = response.headers.get('content-type') || '';
  const data = type.includes('application/json') ? await response.json() : await response.text();
  if (!response.ok) throw new Error(data.message || data || 'Request failed');
  return data;
}
function makeInput(field) {
  const label = document.createElement('label'); label.textContent = field.label;
  let input;
  if (field.type === 'select') {
    input = document.createElement('select');
    const first = document.createElement('option'); first.value=''; first.textContent=`Select ${field.label.toLowerCase()}`; input.appendChild(first);
    api(field.from).then(items => items.forEach(item => { const option=document.createElement('option'); option.value=item[field.id]; option.textContent=item[field.display]; input.appendChild(option); })).catch(() => {});
  } else {
    input = document.createElement('input'); input.type=field.type;
    if (field.step) input.step=field.step; if (field.min !== undefined) input.min=field.min;
  }
  input.name=field.key; input.id=`field-${field.key}`; input.required=!!field.required;
  label.appendChild(input); return label;
}
function drawForm() {
  const config=modules[activeModule]; $('formTitle').textContent=config.title; $('tableTitle').textContent=config.title.replace('Management','Records'); $('moduleEyebrow').textContent=config.number;
  $('saveButton').textContent=`Add ${config.singular}`; $('recordId').value=''; $('formMessage').textContent='';
  $('formFields').replaceChildren(...config.fields.map(makeInput));
  $('cancelButton').hidden=true;
}
function drawTable(items) {
  const config=modules[activeModule];
  $('tableHead').innerHTML=`<tr>${config.columns.map(c=>`<th>${c[1]}</th>`).join('')}<th>Actions</th></tr>`;
  $('recordCount').textContent=`${items.length} record${items.length===1?'':'s'}`;
  const body=$('tableBody'); body.replaceChildren();
  if (!items.length) { const tr=document.createElement('tr'); const td=document.createElement('td'); td.colSpan=config.columns.length+1; td.className='empty'; td.textContent='No records found. Add the first record using the form above.'; tr.appendChild(td); body.appendChild(tr); return; }
  items.forEach(item=>{
    const tr=document.createElement('tr');
    config.columns.forEach(([key])=>{const td=document.createElement('td'); td.textContent=item[key] ?? ''; tr.appendChild(td);});
    const actionCell=document.createElement('td');
    const edit=document.createElement('button'); edit.className='small'; edit.textContent='Edit'; edit.addEventListener('click',()=>editRecord(item));
    const del=document.createElement('button'); del.className='small danger'; del.textContent='Delete'; del.addEventListener('click',()=>deleteRecord(item));
    actionCell.append(edit,del); tr.appendChild(actionCell); body.appendChild(tr);
  });
}
async function loadRecords() {
  try { const data=await api(modules[activeModule].endpoint); drawTable(data); }
  catch(error) { $('tableBody').innerHTML=`<tr><td class="empty">${escapeText(error.message)}. Check that the PostgreSQL tables exist.</td></tr>`; }
}
function escapeText(value) { const div=document.createElement('div'); div.textContent=String(value); return div.innerHTML; }
function recordPrimaryKey(item) {
  const keys = {
    farmers:'farmer_id', fields:'field_id', crops:'crop_id',
    seasons:'season_id', 'yield-records':'record_id',
    cultivation:'activity_id', fertilizers:'input_id',
    irrigation:'irrigation_id', harvests:'harvest_id'
  };
  return item[keys[activeModule]];
}
function editRecord(item) {
  const config=modules[activeModule]; $('recordId').value=recordPrimaryKey(item);
  config.fields.forEach(field=>{ const input=$(`field-${field.key}`); input.value=item[field.source] ?? ''; });
  $('saveButton').textContent=`Update ${config.singular}`; $('cancelButton').hidden=false; $('formTitle').textContent=`Update ${config.singular}`; $('formMessage').textContent='Edit the details and click Update.'; window.scrollTo({top:0,behavior:'smooth'});
}
async function deleteRecord(item) {
  const config=modules[activeModule];
  const id=recordPrimaryKey(item);
  if (!confirm(`Delete this ${config.singular.toLowerCase()} record?`)) return;
  try { const result=await api(`${config.endpoint}/${id}`,{method:'DELETE'}); $('formMessage').textContent=result.message; await loadRecords(); }
  catch(error) { $('formMessage').textContent=error.message; }
}

async function loadReports() {
  const message = $('reportsMessage');
  const container = $('reportsCards');
  message.textContent = 'Loading reports...';
  container.replaceChildren();

  try {
    const data = await api('/api/reports/summary');
    const money = value => new Intl.NumberFormat('en-IN', {
      style: 'currency',
      currency: 'INR',
      maximumFractionDigits: 2
    }).format(Number(value) || 0);

    const quantity = value => new Intl.NumberFormat('en-IN', {
      maximumFractionDigits: 2
    }).format(Number(value) || 0);

    const cards = [
      ['Total Harvest', quantity(data.totalHarvestKg) + ' kg', 'Total harvested quantity'],
      ['Total Revenue', money(data.totalRevenue), 'Revenue from harvest records'],
      ['Other Harvest Cost', money(data.harvestOtherCost), 'Additional harvest expenses'],
      ['Fertilizer Cost', money(data.fertilizerCost), 'Total fertilizer expenses'],
      ['Irrigation Cost', money(data.irrigationCost), 'Total irrigation expenses'],
      ['Harvested Area', quantity(data.harvestedAreaAcres) + ' acres', 'Total cultivated area harvested'],
      ['Productivity', quantity(data.productivityKgPerAcre) + ' kg/acre', 'Harvest per acre'],
      ['Total Cost', money(data.totalCost), 'Combined recorded expenses'],
      ['Profit / Loss', money(data.profit), 'Revenue minus total cost']
    ];

    cards.forEach(([label, value, note]) => {
      const card = document.createElement('article');
      card.className = 'report-card';

      const heading = document.createElement('h3');
      heading.className = 'report-label';
      heading.textContent = label;

      const amount = document.createElement('p');
      amount.className = 'report-value';
      amount.textContent = value;

      const description = document.createElement('p');
      description.className = 'report-note';
      description.textContent = note;

      card.append(heading, amount, description);
      container.appendChild(card);
    });

    message.textContent = 'Reports loaded successfully.';
  } catch (error) {
    message.textContent = 'Unable to load reports: ' + error.message;
  }
}form.addEventListener('submit', async event=>{
  event.preventDefault(); const config=modules[activeModule], id=$('recordId').value; const body={};
  config.fields.forEach(field=>{ const input=$(`field-${field.key}`); let value=input.value; if(field.type==='number'||field.type==='select') value=value===''?null:Number(value); body[field.key]=value; });
  try { const result=await api(id?`${config.endpoint}/${id}`:config.endpoint,{method:id?'PUT':'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify(body)}); $('formMessage').textContent=id?`${config.singular} updated successfully.`:`${config.singular} added successfully.`; form.reset(); $('recordId').value=''; $('saveButton').textContent=`Add ${config.singular}`; $('cancelButton').hidden=true; $('formTitle').textContent=config.title; await loadRecords(); }
  catch(error) { $('formMessage').textContent=error.message; }
});
function showDashboard() {
  activeModule = 'dashboard';
  $('dashboardPanel').hidden = false;
  $('reportsPanel').hidden = true;
  $('managementPanel').hidden = true;
  $('recordsPanel').hidden = true;

  document.querySelectorAll('.tab').forEach(tab => {
    tab.classList.toggle('active', tab.dataset.module === 'dashboard');
  });
}

async function loadDashboard() {
  const message = $('dashboardMessage');
  const container = $('dashboardCards');

  message.textContent = 'Loading farm performance...';

  try {
    const data = await api('/api/reports/summary');

    const money = value => new Intl.NumberFormat('en-IN', {
      style: 'currency',
      currency: 'INR',
      maximumFractionDigits: 2
    }).format(Number(value) || 0);

    const quantity = value => new Intl.NumberFormat('en-IN', {
      maximumFractionDigits: 2
    }).format(Number(value) || 0);

    const metrics = [
      ['Total Harvest', quantity(data.totalHarvestKg) + ' kg', 'Total harvested quantity'],
      ['Total Revenue', money(data.totalRevenue), 'Revenue from harvest records'],
      ['Total Cost', money(data.totalCost), 'Combined recorded expenses'],
      ['Profit / Loss', money(data.profit), 'Revenue minus total cost'],
      ['Harvested Area', quantity(data.harvestedAreaAcres) + ' acres', 'Recorded harvested area'],
      ['Productivity', quantity(data.productivityKgPerAcre) + ' kg/acre', 'Harvest per acre']
    ];

    container.replaceChildren();

    metrics.forEach(([label, value, note]) => {
      const card = document.createElement('article');
      card.className = 'metric-card';

      const heading = document.createElement('span');
      heading.textContent = label;

      const amount = document.createElement('strong');
      amount.textContent = value;

      const description = document.createElement('small');
      description.textContent = note;

      card.append(heading, amount, description);
      container.appendChild(card);
    });

    message.textContent = 'Farm performance updated successfully.';
  } catch (error) {
    message.textContent = 'Unable to load dashboard data: ' + error.message;
  }
}

$('moduleTabs').addEventListener('click', event => {
  const button = event.target.closest('button[data-module]');
  if (!button) return;

  const selectedModule = button.dataset.module;

  if (selectedModule === 'dashboard') {
    showDashboard();
    loadDashboard();
    return;
  }

  document.querySelectorAll('.tab').forEach(tab => {
    tab.classList.toggle('active', tab === button);
  });

  $('dashboardPanel').hidden = true;

  if (selectedModule === 'reports') {
    activeModule = 'reports';
    $('managementPanel').hidden = true;
    $('recordsPanel').hidden = true;
    $('reportsPanel').hidden = false;
    loadReports();
    return;
  }

  activeModule = selectedModule;
  $('reportsPanel').hidden = true;
  $('managementPanel').hidden = false;
  $('recordsPanel').hidden = false;

  form.reset();
  drawForm();
  loadRecords();
});

$('openFarmersButton').addEventListener('click', () => {
  document.querySelector('[data-module="farmers"]').click();
});

$('openReportsButton').addEventListener('click', () => {
  document.querySelector('[data-module="reports"]').click();
});

$('dashboardRefreshButton').addEventListener('click', loadDashboard);
$('cancelButton').addEventListener('click', () => {
  form.reset();
  drawForm();
});
$('refreshButton').addEventListener('click', loadRecords);
$('reportsRefreshButton').addEventListener('click', loadReports);

$('healthButton').addEventListener('click', async () => {
  try {
    $('healthResult').textContent = JSON.stringify(
      await api('/api/health'),
      null,
      2
    );
  } catch (error) {
    $('healthResult').textContent = error.message;
  }
});

showDashboard();
loadDashboard();
