const BaseRepository = require("./BaseRepository");

class CompanyRepository extends BaseRepository{

    constructor(axios){
        super(axios, 'companies')
    }

    find(query){
        return super.find(query);

        
    }

}

module.exports = CompanyRepository