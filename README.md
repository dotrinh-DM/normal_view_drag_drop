# normal_view_drag_drop
Mấu chốt của vấn đề là: 
- Bước 1. Muốn drag view nào thì cho phép nó drag bằng cách gọi hàm startDrag() hoặc startDragAndDrop() Android 7.0 trở lên.
- Bước 2. Sau đấy xác định target đến là 1 viewgroup hoặc view bất kì. 
Xác định bằng cách nào? Xác định bằng cách gọi hàm setOnDragListener() để có callback tại hàm onDrag() cho mình, khi cái view ở bước 1 nó đc drop
  - ACTION_DRAG_STARTED: bat dau keo vao khu vuc view lang nghe
  - ACTION_DRAG_LOCATION: di chuyen trong khu vuc view lang nghe
  - ACTION_DROP: drop vao khu vuc view lang nghe thanh cong
  - ACTION_DRAG_ENDED: ket thuc thanh cong or ko thanh cong khu vuc view lang nghe
  - ACTION_DRAG_ENTERED: vào khu vuc view lang nghe
  - ACTION_DRAG_EXITED: ra khỏi khu vuc view lang nghe
