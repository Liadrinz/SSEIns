function reqItem(resName, range, method, pk, data, callback = (res, error) => { }, async = true) {
    var url = urlRoot + resName + "/" + (pk === null ? "" : pk) + (range === null ? "" : `/${range[0]}/${range[1]}`);
    if (method === 'POST' || method === 'PUT') {
        $.ajax({
            url: url,
            method: method,
            async: async,
            headers: {
                "Content-Type": "application/json"
            },
            data: JSON.stringify(data),
            success(res) {
                callback(res, false);
            },
            error(info) {
                callback(null, info);
            }
        });
    } else {
        $.ajax({
            url: url,
            method: method,
            async: async,
            success(res) {
                callback(res, false);
            },
            error(info) {
                callback(null, info);
            }
        });
    }
}

var restClient = {
    getList(resName, range, callback = (res, error) => { }, async = true) {
        reqItem(resName, range, "GET", null, null, callback = callback, async = async);
    },

    getItem(resName, pk, callback = (res, error) => { }, async = true) {
        reqItem(resName, null, "GET", pk = pk, data = null, callback = callback, async = async);
    },

    post(resName, data, callback = (res, error) => { }, async = true) {
        reqItem(resName, null, "POST", null, data = data, callback = callback, async = async);
    },

    put(resName, pk, data, callback = (res, error) => { }, async = true) {
        reqItem(resName, null, "PUT", pk = pk, data = data, callback = callback, async = async);
    },

    delete(resName, pk, callback = (res, error) => { }, async = true) {
        reqItem(resName, null, "DELETE", pk = pk, callback = callback, async = async);
    }
}