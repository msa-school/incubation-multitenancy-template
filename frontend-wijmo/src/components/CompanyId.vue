<template>
    <div>
        <BasePicker v-if="editMode" v-model="value" searchApiPath="companies/search/findByCompanyQuery" searchParameterName="name" idField="code" nameField="name" path="companies" @selected="pick" :editMode="editMode" />
        <div v-else>
            <span>{{ value.name }}</span>
        </div>
    </div>
</template>

<script>
const axios = require('axios').default;
import BaseRepository from '../repository/BaseRepository';
    export default {
        name: 'CompanyId',
        components:{},
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
            inList: Boolean,
            label: String,
        },
        data: () => ({
        }),
        watch: {
            value(val) {
                this.$emit('input', val);
            }
        },
        async created(){
            this.repository = new BaseRepository(axios, "companies")
            this.value = await this.repository.findById(this.value.id)
        },

        methods: {
            async pick(val){
                this.newValue = val;
            },
        }
    }
</script>

