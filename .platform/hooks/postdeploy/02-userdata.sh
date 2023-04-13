#!/bin/bash
echo "ewogICJ0eXBlIjogImV4dGVybmFsX2FjY291bnQiLAogICJhdWRpZW5jZSI6ICIvL2lhbS5nb29nbGVhcGlzLmNvbS9wcm9qZWN0cy8zNjMyNDI1NTE5ODMvbG9jYXRpb25zL2dsb2JhbC93b3JrbG9hZElkZW50aXR5UG9vbHMvYXdzLXdvcmtsb2Fkcy9wcm92aWRlcnMvYXdzLWhlbGxvLXdvcmxkIiwKICAic3ViamVjdF90b2tlbl90eXBlIjogInVybjppZXRmOnBhcmFtczphd3M6dG9rZW4tdHlwZTphd3M0X3JlcXVlc3QiLAogICJzZXJ2aWNlX2FjY291bnRfaW1wZXJzb25hdGlvbl91cmwiOiAiaHR0cHM6Ly9pYW1jcmVkZW50aWFscy5nb29nbGVhcGlzLmNvbS92MS9wcm9qZWN0cy8tL3NlcnZpY2VBY2NvdW50cy9hd3MtaGVsbG8td29ybGQtaW50ZXJhY3Rpb24tM0B3b3JrbG9hZC1pZGVudGl0eS1wb29sLW1hbmFnZXIuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb206Z2VuZXJhdGVBY2Nlc3NUb2tlbiIsCiAgInRva2VuX3VybCI6ICJodHRwczovL3N0cy5nb29nbGVhcGlzLmNvbS92MS90b2tlbiIsCiAgImNyZWRlbnRpYWxfc291cmNlIjogewogICAgImVudmlyb25tZW50X2lkIjogImF3czEiLAogICAgInJlZ2lvbl91cmwiOiAiaHR0cDovLzE2OS4yNTQuMTY5LjI1NC9sYXRlc3QvbWV0YS1kYXRhL3BsYWNlbWVudC9hdmFpbGFiaWxpdHktem9uZSIsCiAgICAidXJsIjogImh0dHA6Ly8xNjkuMjU0LjE2OS4yNTQvbGF0ZXN0L21ldGEtZGF0YS9pYW0vc2VjdXJpdHktY3JlZGVudGlhbHMiLAogICAgInJlZ2lvbmFsX2NyZWRfdmVyaWZpY2F0aW9uX3VybCI6ICJodHRwczovL3N0cy57cmVnaW9ufS5hbWF6b25hd3MuY29tP0FjdGlvbj1HZXRDYWxsZXJJZGVudGl0eSZWZXJzaW9uPTIwMTEtMDYtMTUiCiAgfQp9" | base64 --decode > /var/app/current/google-cred.json
echo "ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiaGVsbG93b3JsZGxlYXJuLTM3ODAwOCIsCiAgInByaXZhdGVfa2V5X2lkIjogImRiZDJkYTIzNzQwZTc4ODM4MjcwODMyYWNiMGU2YmM1ZjFlMmE4MDgiLAogICJwcml2YXRlX2tleSI6ICItLS0tLUJFR0lOIFBSSVZBVEUgS0VZLS0tLS1cbk1JSUV1d0lCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktVd2dnU2hBZ0VBQW9JQkFRQ0ltczlFOXlLQlhIMmpcbkFuUEkxUzc5Y3Y4bzY3MmVVM0d2RUtmaWNMT05EMjB0UTdBNVJ6WlAxMWtYbkNOa3Z0QnhTd2J5ajhUWEZxaUZcblg5V2RKamEwc1VjK05nY244bVBHTkNxcWZUUXBUanljbkdpOFpRNDE3WVJnMytUVUhGaWZTamF3UkY4TUNXWTFcbkdDc3B4UGlraDREU2pBZnh4alRzTDcxd0ZsTS9FdTBkVGhsREYyV3JrQU9MeG9vWWdyS0VLOWwxY0NBRTNsQVlcbnVQVTJxamlMVGFWZFBkZis0UmRnM29TNFdsQ1p2bzBYTmlSUEYwYVpSSXFQaEV6dFhBN2tSZmY3Tkp0TEZ6cHZcbnFXQ0FtTEdkMW5DeWlVQjNXSHAvenlaWUlPeEdSREgwbCthYmlCQnFndzN3Yit4Ly9YRGl0UDV6NlBZdTdLYTZcbnVEa1llRGVKQWdNQkFBRUNnZjgvaDNuTDU0dm5kTFpoN1dJSXBIbG5kdWdLNHNZeWFpSDlib1IydkQ3RWh3M3FcbjAzdVNuQ1hkVXd3Ny9UdUVQdWdJMzQwUzNxRVNXd3Vobm1kbGEyTVhLMmFvQlJWM25ubk9KU2tEK0NUdjFwaVpcbnBic3lzKytOd2dKMFhKUjAxcGNQQ1NBOU1OU3h0ZEErVDQyc0N1WUFiOG4yZmk3NWVTdXBjY1NDUjkrdkxFcGJcbnpKb21Lckh6a0RHTnN6K0g0TWkvVWN4aE15cGd4UzFCYVhvdFJUR01tT2ZlRGU3eDZOOFRmNzVBd0NTNDJ0MDFcbmlENEhETHp5d3lKaEFJOE9oT1pzMm5FVjdBYjVtSHg3VXJ6UnZSOEROcmZNT21oS0JVMFlVajc5Ymc1WHRCWTZcbkdoTEJ4c0lRSHUvMVZsaVEwMFRKMW9LK3ZVKzdIaHA5aWs1MzA5RUNnWUVBdk1WSHRuSUt0Y3JXWHRkZzV4ZnVcbkx4YlBDeHZEMFNlRVZIQ0wraVJESlFuMU1UcWQyREp5Ylp0ZjQrZnBUZ0JicFVqRXdUZm5ueVZvRzdFdWVpTStcbkRyWGRWTjVMOGhQVnkvNkJZTSswbHRMcHNGWEZ5RzJHUDhFR2ZaTUNqUWw1UUFEMFZxVEFFNU9lc2trNVc4WXNcbmd5RTJzcmkrU0V6ck9nM2V3S0pjcFRrQ2dZRUF1VUZzK1JvZDUvNTM1V2FDS2ExYWlZOHpndUkzZk1HVjRGVXRcbkk5VS9tWmxOSFg1RVFzQVdtdklmTllTdkFObU9CSitoS2NrVGtkdFE0Wm84Y3B4bldSN0pyd3ZOOUt2UUJFRWpcbkNBS05sb3pzVGZ2NDUrTDZlWENNMStVRXFuS0E5bk5lRy92a3czNjVXa2tjcFZBUDRzamMrTElBMlJrRGQzQkNcbnRPc3o5TkVDZ1lBN1l5MWVmcDVUOXc1ZUpCQStZODd4VFpHeGd2aWw4bksxUmZzanBRaml2REZCWFptUERML25cbkw3eUpMTXEvV1dsN3BGc1NGbzV1Q1F5cHVhSlBsb3JOZ2ZXeDM0V3hFUUJ2L3crZzczZDNBNysxVkNhM0dZM2tcbnZNdFg4a29aS09QV2VQWmwwOHNCM2ExTmxhL1A2WmJUWWoyZEdIWDB0UTVENDEweTZXR1FNUUtCZ0ZtN1Z3MVJcbm1TckdGMDBHV2dYVjhnU3ZXRVFOVHRNZThYbTN5Q1dUSlR0QkJobEREODFGUGEzUG1VNVM5bkZ5NzFkTitzSkNcbm96Z0RBeXBEaXdQNHZoWnMwMFAyYzh3UGJMS2kybFVHNTQ0RXRIRVJtMzYyY3B4ZjU5aXJGWmQ1M3YyQTVCU3BcbjdpVERQUVIrWldHdFpnUnpScFV2b3ptRTVDajJOMWxxazhBQkFvR0JBSWx3M3hGZG5sYnJNK202SmgzM2YxUjFcbmZMd0hEZWhjUjh2anRQRFBGNVZxMzFBQUpVV1lGSFhHY05FU3VDV2dBNnB4U1MyN0hGUFgyTlNUd0hieHhRd2hcbnZud2VFbkFmeTFZUnpiVVBqazNZUlBmQzhHbkxIMzRFT3hWZFh6elZMTWZiQlNqSkZDRlhzSVh5ZFhKN3ozS1ZcbnBhNFNQYzRvbjVkbUlLZlNQb0RwXG4tLS0tLUVORCBQUklWQVRFIEtFWS0tLS0tXG4iLAogICJjbGllbnRfZW1haWwiOiAiaHdsLWFwaS1zZXJ2aWNlQGhlbGxvd29ybGRsZWFybi0zNzgwMDguaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICJjbGllbnRfaWQiOiAiMTA3MzAzMTY1MjI5OTI4MjAyNDkyIiwKICAiYXV0aF91cmkiOiAiaHR0cHM6Ly9hY2NvdW50cy5nb29nbGUuY29tL28vb2F1dGgyL2F1dGgiLAogICJ0b2tlbl91cmkiOiAiaHR0cHM6Ly9vYXV0aDIuZ29vZ2xlYXBpcy5jb20vdG9rZW4iLAogICJhdXRoX3Byb3ZpZGVyX3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vb2F1dGgyL3YxL2NlcnRzIiwKICAiY2xpZW50X3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vcm9ib3QvdjEvbWV0YWRhdGEveDUwOS9od2wtYXBpLXNlcnZpY2UlNDBoZWxsb3dvcmxkbGVhcm4tMzc4MDA4LmlhbS5nc2VydmljZWFjY291bnQuY29tIgp9" | base64 --decode > /var/app/current/sa-cred.json