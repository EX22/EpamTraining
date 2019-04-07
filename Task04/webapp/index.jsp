<h1> Welcome to the Greenhouse </h1>


<form method="post" enctype="multipart/form-data" action="uploadXML">
<table>
<tr>
<td>
    Select file to upload:
    <input type="file" name="fileToUpload" id="fileToUpload">
    </td>
    </tr>

    <tr>
        <td>

    <select name="parser">
          <option value="SAX">SAX parser</option>
          <option value="StAX">StAX parser</option>
          <option value="DOM">DOM parser</option>
    </select>

    </td>
        </tr>

        <tr>
            <td>

    <input type="submit" value="Upload XML" name="submit">

    </td>
        </tr>

       </table>


</form>

