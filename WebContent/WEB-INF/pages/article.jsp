<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>CKEditor 5 - Classic editor</title>
<script
	src="https://cdn.ckeditor.com/ckeditor5/18.0.0/classic/ckeditor.js"></script>
</head>
<body>
	<br /> ${mycontent}
	<br />
	<hr />
	<h1>Classic editor</h1>
	<form action="XXXcontroller" method="post">
		<table>
			<tr>
				<td><p>your account id:</p></td>
				<td><input type="text" name="accountId"
					value="insert youer title"></td>
			</tr>
			<tr>
				<td><p>article title:</p></td>
				<td><input type="text" name="articletitle"
					value="insert youer title"></td>
			</tr>
			<tr>
				<td><p>article parent id:</p></td>
				<td><input type="text" name="articleParentId"
					value="insert youer title"></td>
			</tr>
			<tr>
				<td><p>article location:</p></td>
				<td><input type="text" name="articlelocation"
					value="insert youer title"></td>
			</tr>
		</table>

		<textarea name="content" id="editor">
            ${mycontent}
        </textarea>
		<p>
			<input type="submit" value="Submit">
		</p>
	</form>
	<script>
        ClassicEditor
            .create( document.querySelector( '#editor' ),{
                mediaEmbed:{
                	previewsInData:true
                    }
                } )
            .catch( error => {
                console.error( error );
            } );
        CKEDITOR.config.extraPlugins = 'myplugin,anotherplugin';
    </script>
</body>
</html>