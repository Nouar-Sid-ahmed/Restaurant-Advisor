<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\User;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Facades\Hash;

class UsersController extends Controller
{
    function getAll() {
        return response()->json((user::all()), 200);
    }
    function create(Request $request){
        $validator = Validator::make($request->all(), [
            'name' => 'required',
            'email' => ['required','unique:users'],
            'password' => 'required',
            'firstname' => 'required',
            'username' => ['required','unique:users'],
            'age' => 'required'
        ]);

        if ($validator->fails()) {
            return response()->json([
                'message' => "A fiels is missing"
            ],400);
        } else {
            $user = new User();

            $user->name = $request->name;
            $user->email = $request->email;
            $user->password = Hash::make($request->password);
            $user->firstname = $request->firstname;
            $user->username = $request->username;
            $user->age = $request->age;

            $user->save();

            return response($user, 201);
        }
    }

    function auth(Request $request){

        $validator = Validator::make($request->all(), [
            'email' => 'nullable',
            'password' => 'required',
            'username' => 'nullable'
        ]);

        if ($validator->fails()) {
            return response()->json([
                'message' => "A fiels is missing"
            ],400);
        }

        $user = user::where('username',$request->username)->get()->first();

        if($user){
            if (Hash::check($request->password, $user->password)){
                return response($user, 200);
            } else {
                return response()->json([
                    'message' => "bad password or bad username"
                ],400);
            }
        }else {
            $user = User::where('email',$request->email)->get()->first();
            if ($user){
                if (Hash::check($request->password, $user->password)){
                    return response($user, 200);
                } else {
                    return response()->json([
                        'message' => "bad password or bad email"
                    ],400);
                }
            } else {
                return response()->json([
                    'message' => "user name or email doesn't exist"
                ],400);
            }
        }
    }
}
