const app = angular.module("cart-app", []);

app.controller("cart-controller", function($scope, $http){
	/*CART*/
	$scope.cart = {
		items: [],
		
		// thêm vào giỏ hàng
		add(id){
			var item = this.items.find(item => item.id == id); // tìm kiếm xem có sản phẩm nào hay chưa
			if(item){
				// nếu có
				item.qty++; // tăng số lượng
				this.save();
			} else{
				$http.get(`/rest/products/${id}`).then(resp => { // chưa có thì tải trên server về
					resp.data.qty = 1;
					this.items.push(resp.data); // thêm vào danh sách
					this.save();
				})
			}
		},
		
		// lưu
		save(){
			var json = JSON.stringify(angular.copy(this.items)); // đổi các mặc hàng sang Json
			localStorage.setItem("cart", json); // lưu vào localStorage
		},
		
		// đọc giỏ hàng từ local Storage
		loadFrom(){
			var json = localStorage.getItem("cart"); // đọc lại cart trong local 
			this.items = json ? JSON.parse(json):[]; // nếu có chuyển nó sang json vào trong items
		},
		
		get count(){
			return this.items // duyệt các mặt hàng
				.map(item => item.qty) // lấy số lượng
				.reduce((total, qty) => total += qty, 0); // tính tổng
		},
		
		get amount(){
			return this.items
				.map(item => item.qty * item.price) // thành tiền
				.reduce((total, qty) => total += qty, 0);
		},
		
		remove(id){
			var index = this.items.findIndex(item => item.id == id); // tìm kiếm sản phẩm
			this.items.splice(index, 1); // sử dụng plice để xóa
			this.save();
		},
		
		clear(){
			this.items= []; // items rổng
			this.save();
		}
	}
	
	$scope.cart.loadFrom();
	
	/*CART-END*/
	
	/*ORDER*/
	$scope.order = {
		createDate: new Date(),
		
		address: "",
		
		account: {username: $("#username").text()}, // chuyển đối tượng user
		
		get orderDetails(){ // thuộc tính chỉ đạo
			return $scope.cart.items.map(item => {
				return {
					// duyệt các mặt hàng
					product:{id: item.id},
					price:item.price,
					quantity:item.qty
				}
			});
		},
		
		purchase(){
			var order = angular.copy(this); // lấy ra được order hiện tại
			// thực hiện đặt hàng
			$http.post("/rest/orders", order).then(resp => { // post lên địa chỉ 
				alert("Đặt hàng thành công!");
				$scope.cart.clear();
				location.href = "/order/detail/" + resp.data.id; // chuyển trang
			}).catch(error => {
				alert("Đặt hàng lỗi!");
				console.log(error);
			})
		}
	}
	/*ORDER-END*/
})