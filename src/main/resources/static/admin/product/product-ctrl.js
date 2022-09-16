app.controller("product-ctrl", function($scope, $http) {
	$scope.items = [];

	$scope.form = {}; // các đối tượng hiển thị lên form

	$scope.cates = [];

	$scope.initialize = function() {
		// load product
		$http.get("/rest/products").then(resp => { // sản phẩm từ url
			$scope.items = resp.data; // lấy dữ liệu bỏ vào items
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate) // chuyển đổi ngày
			})
		});

		// load category
		$http.get("/rest/categories").then(resp => { // sản phẩm từ url
			$scope.cates = resp.data; // lấy dữ liệu bỏ vào items
		});
	}

	$scope.initialize();

	$scope.reset = function() {
		$scope.form = {
			createDate: new Date(),
			image: 'cloud-upload.jpg',
			available: true
		};
	}

	$scope.edit = function(item) {
		$scope.form = angular.copy(item); // copy item
		$(".nav-tabs a:eq(0)").tab('show'); // chuyển lại tab đầu tiên
	}

	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post('/rest/products', item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate);
			$scope.items.push(resp.data); // thêm vào trong list items
			$scope.reset();
			$scope.initialize();
			alert("Thêm mới thành công")
		}).catch(error => {
			alert("Thêm mới thất bại");
			console.log("Error", error);
		});
	}

	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/products/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id)
			$scope.items[index] = item;
			$scope.reset();
			alert("Cập nhật thành công")
		}).catch(error => {
			alert("Cập nhật thất bại");
			console.log("Error", error);
		});
	}

	$scope.delete = function(item) {
		$http.delete(`/rest/products/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id)
			$scope.items.splice(index, 1);
			$scope.reset();
			alert("Xóa thành công")
		}).catch(error => {
			alert("Xóa thất bại");
			console.log("Error", error);
		});
	}

	$scope.imageChanged = function(files) {
		var data = new FormData(); // đối tương FormData
		data.append('file', files[0]); // chọn file bỏ vào FormData
		$http.post('/rest/upload/images', data, { // post lên server
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image = resp.data.name; // trả về name
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}

	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size; // vị trí bắt đầu
			return $scope.items.slice(start, start + this.size); // lấy sản phẩm bên trong items
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size); // chia và làm tròn cho 1 vì sợ 2 số nguyên chia nhau
		},

		first() {
			this.page = 0;
		},

		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},

		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},

		last() {
			this.page = this.count - 1;
		}
	}
});