<template>
    <div>
        <div class="label-title">SalesPerson</div>
        <String label="입력하세요." v-model="value.salesPerson" :editMode="editMode"/>
        <SalesType offline label="SalesType" v-model="value.salesType" :editMode="editMode" @change="change"/>
        <CompanyId offline label="CompanyId" v-model="value.companyId" :editMode="editMode" @change="change"/>
        <SalesItemManager offline label="SalesItem" v-model="value.salesItem" :editMode="editMode" @change="change"/>
        <v-divider class="border-opacity-100 my-divider"></v-divider>
        <v-layout row justify-end>
            <v-btn
                width="64px"
                color="primary"
                @click="save"
            >
                저장
            </v-btn>
        </v-layout>

        <v-snackbar
            v-model="snackbar.status"
            :top="true"
            :timeout="snackbar.timeout"
            color="error"
        >
            {{ snackbar.text }}
            <v-btn dark text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
    </div>
</template>

<script>

import BaseEntity from './base-ui/BaseEntity'


export default {
    name: 'SalesOrder',
    mixins:[BaseEntity],
    components:{
    },
    data: () => ({
        path: "salesOrders"
    }),
    created(){


    },
    computed:{
    },
    methods: {

    },
}
</script>

