<!DOCTYPE html>
<html>
<body>
<table id="table" border="1">
	<tr>
		<td>Rio</td><td id="Rio"></td>
	</tr>
	<tr>
		<td>Paris</td><td id="Paris"></td>
	</tr>
	<tr>
		<td>London</td><td id="London"></td>
	</tr>
</table>
</body>
<script type="text/javascript">
	var evtSource = new EventSource("/ssehouseprices");
	evtSource.onmessage = function(e) {
		housePrice = JSON.parse(e.data);
		document.getElementById(housePrice.city).innerHTML = housePrice.pricePerSquare;
	}
</script>
</html>