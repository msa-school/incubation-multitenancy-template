<template>
    <div style="max-height:80vh;">
        <div class="gs-bundle-of-buttons" style="max-height:10vh;">
            <v-btn @click="addNewRow" small color="primary">
                <v-icon small>mdi-plus-circle-outline</v-icon>등록
            </v-btn>
            <v-btn  @click="editSelectedRow" small color="primary">
                <v-icon small>mdi-pencil</v-icon>수정
            </v-btn>
            <v-btn @click="deleteSelectedRows" small color="primary">
                <v-icon small>mdi-minus-circle-outline</v-icon>삭제
            </v-btn>
            <excel-export-button :exportService="this.exportService" :getFlex="getFlex" />
        </div>

        <!-- the grid -->
        <wj-flex-grid
            ref="flexGrid"
            :autoGenerateColumns="false"
            :allowAddNew="false"
            :allowDelete="true"
            :allowPinning="'SingleColumn'"
            :newRowAtTop="false"
            :showMarquee="true"
            :selectionMode="'MultiRange'"
            :validateEdits="false"
            :itemsSource="values"
            :initialized="flexInitialized"
            :selectionChanged="onSelectionChanged"
            style="margin-top:10px; max-height:65vh;"
            class="wj-felx-grid"
        >
            <wj-flex-grid-filter :filterColumns="['RowHeader', 'salesPerson','salesType','salesItem','companyId',]" />
            
            <wj-flex-grid-cell-template cellType="RowHeader" v-slot="cell">{{cell.row.index + 1}}</wj-flex-grid-cell-template>
            <wj-flex-grid-column binding="salesPerson" header="salesPerson" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="salesType" header="salesType" width="2*" :isReadOnly="true" align="center" />

            <wj-flex-grid-column binding="companyId" header="company" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <CompanyId :editMode="false" v-model="cell.item.companyId"></CompanyId>
                </wj-flex-grid-cell-template>
            </wj-flex-grid-column>
        </wj-flex-grid>
        <SalesItemsDetailGrid v-if="selectedRow" v-model="selectedRow.salesItem"/>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
                class="add-edit-dialog"
            >
                <template>
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">SalesOrder 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="openDialog = false"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                    <SalesOrder :offline="offline"
                        :isNew="!itemToEdit"
                        :editMode="true"
                        v-model="itemToEdit"
                        @add="append"
                    />
                    </v-card-text>
                    <v-card-actions class="justify-end">
                    
                    </v-card-actions>
                </v-card>
                </template>
            </v-dialog>
        </v-col>
        <v-col style="margin-bottom:40px;">
            <div class="text-center">
                <v-dialog
                    width="332.5"
                    fullscreen
                    hide-overlay
                    transition="dialog-bottom-transition"
                >
                    
                    <v-btn
                        style="position:absolute; top:2%; right:2%"
                        @click="closeDialog()"
                        depressed
                        icon 
                        absolute
                    >
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-dialog>
            </div>
        </v-col>
    </div>
</template>

<script>

import BaseGrid from '../base-ui/BaseGrid'
import SalesOrder from '../SalesOrder.vue'
import SalesItemsDetailGrid from './SalesItemsDetailGrid.vue'
import { ExportService } from '../base-ui/export'

export default {
    name : 'salesOrder-grid',
    mixins:[BaseGrid],
    components:{
    SalesOrder,
    SalesItemsDetailGrid
},
    data: () => ({
        path: 'salesOrders',
        exportService: new ExportService(),
    }),

}
</script>

